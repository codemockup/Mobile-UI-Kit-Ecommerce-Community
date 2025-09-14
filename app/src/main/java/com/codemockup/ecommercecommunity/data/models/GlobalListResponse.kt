package com.codemockup.ecommercecommunity.data.models

/**
 * Global list data fetch structure with pagination
 * example usage:
 *  Response<GlobalListResponse<Bean>>
 */
data class GlobalListResponse<T>(
    val totalCount: Int? = null,
    val pageSize: Int? = null,
    val currentPage: Int? = null,
    val totalPages: Int? = null,
    val items: List<T>? = null
)