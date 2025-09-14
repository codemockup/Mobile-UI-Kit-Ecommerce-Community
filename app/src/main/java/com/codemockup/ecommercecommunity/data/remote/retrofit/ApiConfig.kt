package com.codemockup.ecommercecommunity.data.remote.retrofit

import com.codemockup.ecommercecommunity.BuildConfig
import com.codemockup.ecommercecommunity.data.remote.ApiService
import com.codemockup.ecommercecommunity.utils.sentry.SentryCurlCapturingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// handle api request
class ApiConfig {
    companion object {

        private const val TIME_OUT: Long = 30

        fun getApiService(): ApiService {
            // Set up logging for debugging (only in debug mode)
            val loggingInterceptor: HttpLoggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)  // Log full request/response body in debug mode
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)  // Disable logging in release mode
            }

            /// sentry interceptor
            val sentryCurlInterceptor = SentryCurlCapturingInterceptor()

            // Build the OkHttpClient with the logging
            val client = OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor = loggingInterceptor)
                .addInterceptor(interceptor = sentryCurlInterceptor)
                .build()

            // Set up Retrofit with the base URL, Gson converter, and OkHttp client
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())// Add Gson for JSON conversion
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}