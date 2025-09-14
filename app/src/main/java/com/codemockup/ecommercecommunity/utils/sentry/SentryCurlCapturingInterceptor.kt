package com.codemockup.ecommercecommunity.utils.sentry

import com.codemockup.ecommercecommunity.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer

class SentryCurlCapturingInterceptor : Interceptor {

    /**
     * Called for each request made by the client
     * @param chain retrofit chain of interceptors and the original request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val curlCommand = generateCurlCommand(request)

        /// extract request body
        val requestBody = request.body?.let { body ->
            val buffer = Buffer()
            body.writeTo(buffer)
            buffer.readUtf8()
        } ?: "No Body"

        /// check if request has token
        val hasToken = request.header("Authorization")?.isNotEmpty() == true

        try {
            /// actual response
            val response = chain.proceed(request)

            /// capture response body
            val responseBodyString = response.body.string() ?: "No Response"

            /// capture api breadcrumb
            SentryLogger.apiBreadcrumb(
                url = request.url.toString(),
                method = request.method,
                statusCode = response.code.toString(),
                hasToken = hasToken
            )

            if (!response.isSuccessful) {
                /// capture api error
                SentryLogger.customContext(
                    title = "CURL",
                    content = mapOf(
                        "URL" to request.url.toString(),
                        "CURL" to curlCommand,
                        "Has Token" to hasToken.toString(),
                        "Method" to request.method,
                        "Request Body" to requestBody,
                        "Response Code" to response.code.toString(),
                        "Response Body" to responseBodyString,
                        "Error Message" to response.message,
                    ),
                    exceptionError = Exception("Request Exception ${response.code}")
                )
            }

            // rebuild response body since .string() modify it
            return response.newBuilder()
                .body(responseBodyString.toResponseBody(response.body.contentType()))
                .build()
        } catch (e: Exception) {
            SentryLogger.customContext(
                title = "CURL",
                content = mapOf(
                    "URL" to request.url.toString(),
                    "CURL" to curlCommand,
                    "Has Token" to hasToken.toString(),
                    "Method" to request.method,
                    "Request Body" to requestBody,
                    "Error Message" to e.message.toString(),
                    "Stacktrace" to e.stackTraceToString()
                ),
                exceptionError = e
            )
            throw e
        }
    }

    /**
     * Generate a curl command from a request
     * @param request the request data to generate the curl command from
     */
    private fun generateCurlCommand(request: Request): String {
        val command = StringBuilder("curl -X ${request.method}")

        // add headers info
        request.headers.forEach { (name, value) ->
            val headerValue = when {
                name.equals("Authorization", ignoreCase = true) -> "[HIDDEN]"
                name.equals("Cookie", ignoreCase = true) -> "[HIDDEN]"
                else -> value
            }
            command.append(" -H \"$name: $headerValue\"")
        }

        // add request body if exist
        request.body?.let { body ->
            try {
                // copy the body to prevent tampering it
                val buffer = Buffer().apply { body.writeTo(this) }
                val rawBody = buffer.readUtf8()
                /// hide sensitive information on release version
                val content = if (BuildConfig.DEBUG) rawBody else hideSensitiveInfo(rawBody)
                command.append(" -d '$content'")
            } catch (_: Exception) {
                command.append(" -d '[BODY UNAVAILABLE]'")
            }
        }


        // Add the URL
        command.append(" \"${request.url}\"")

        return command.toString()
    }

    /**
     * Hide sensitive information from a string
     */
    private fun hideSensitiveInfo(content: String): String {
        var hidden = content

        /// list of sensitive parameters to hide
        val sensitiveParams = listOf(
            "password", "secret", "token", "access_token", "refresh_token", "credential"
        )

        /// handle JSON format
        sensitiveParams.forEach { param ->
            /// obscure matched param
            hidden = hidden.replace(
                Regex("\"$param\"\\s*:\\s*\"[^\"]*\""),
                "\"$param\":\"[HIDDEN]\""
            )
        }

        /// handle form-urlencoded format
        sensitiveParams.forEach { param ->
            hidden = hidden.replace(
                Regex("$param=[^&]+(&|$)"),
                "$param=[HIDDEN]$1"
            )
        }

        return hidden
    }
}