package com.codemockup.ecommercecommunity.data.models

/**
 * Global data fetch structure
 * example usage:
 *  Response<GlobalDataResponse<Bean>>
 */
data class GlobalDataResponse<T>(
    val statusCode: Int? = null,
    val message: String? = null,
    val error: String? = null,
    val data: T? = null,
)