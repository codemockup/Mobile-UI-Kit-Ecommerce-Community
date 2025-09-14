package com.codemockup.ecommercecommunity.utils.helper

import com.google.gson.Gson
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

object TypeConverters {
    /// convert object into map
    private val gson = Gson()

    /**
     * Convert object into Map<String, String>
     *     convert non string value into string
     */
    fun <T : Any> toMap(obj: T): Map<String, String> {
        return (obj::class as KClass<T>).memberProperties.associate { prop ->
            prop.name to (prop.get(obj)?.let { value ->
                val value = when {
                    value::class.isData -> toMap(value).toString()
                    value is String -> value
                    value is List<*> -> value.joinToString(", ")
                    else -> value.toString()
                }
                Logger.i("${prop.name} : $value")
                value
            } ?: "")
        }
    }

    /// convert object to json string
    fun <T> fromObject(obj: T?): String? = gson.toJson(obj)

    /// convert json string to object
    fun <T> toObject(json: String?, type: Class<T>): T? {
        return gson.fromJson(json, type)
    }

}