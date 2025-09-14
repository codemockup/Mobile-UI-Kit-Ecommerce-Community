package com.codemockup.ecommercecommunity.data.repositories

import com.codemockup.ecommercecommunity.common.states.ResultState
import com.codemockup.ecommercecommunity.data.providers.StringResourceProvider
import com.codemockup.ecommercecommunity.utils.helper.Logger
import org.koin.core.component.KoinComponent
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * abstract base repository to provides method for safely API calls
 * handles network exceptions e.g timeouts, no internet connection,
 * and unexpected errors.
 *
 * return [ResultState] responses.
 *
 * @property stringProvider used to access string resources for error messages.
 *
 * usage:
 * class ExampleRepository(
 *     stringProvider: StringResourceProvider,
 * ) : BaseRepository(stringProvider = stringProvider) { ... }
 */
abstract class BaseRepository(private val stringProvider: StringResourceProvider) : KoinComponent {
    companion object {
        private const val TAG = "SAFE_API_CALL"
    }

    /**
     * Executes a suspending API call and safely handles any exceptions that may occur.
     *
     * this function wrap API call and catch [SocketTimeoutException], [IOException],
     * and [Exception] to handle error messages and status codes
     *
     * @param T type of data expected in the result
     * @param apiCall API call to be executed
     * @return [ResultState] contains success or error state based on the response
     *
     * usage:
     * return safeApiCall{
     *  /// normal api calls
     * }
     */
    suspend fun <T> safeApiCall(apiCall: suspend () -> ResultState<T>): ResultState<T> {
        return try {
            apiCall()
        } catch (e: SocketTimeoutException) {
            Logger.e(TAG, "Request Timed Out: ${e.localizedMessage}")
            ResultState.Error(
                statusCode = 408,
                message = stringProvider.getRequestTimedOutMessage()
            )
        } catch (e: IOException) {
            Logger.e(TAG, "No internet connection: ${e.localizedMessage}")
            ResultState.Error(
                statusCode = 503,
                message = stringProvider.getNoInternetMessage()
            )
        } catch (e: Exception) {
            Logger.e(TAG, "${e.localizedMessage} :: ${e.stackTraceToString()}")
            ResultState.Error(
                statusCode = 500,
                message = "${stringProvider.getNetworkErrorMessage()} ${e.localizedMessage ?: stringProvider.getUnknownErrorMessage()}"
            )

        }
    }
}