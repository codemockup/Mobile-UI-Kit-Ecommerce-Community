package com.codemockup.ecommercecommunity.utils

import android.content.Context
import androidx.core.content.edit
import com.codemockup.ecommercecommunity.common.constants.SharedPreferenceKeys
import com.codemockup.ecommercecommunity.utils.helper.Logger
import com.google.gson.Gson
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.KProperty1

object SharedPreference : KoinComponent {
    internal val context: Context by inject()
    private val gson by lazy { Gson() }

    /**
     * Save primitive data types (String, Int, Boolean) to SharedPreferences
     *
     * @param key the key used to store the data in SharedPreferences
     * @param data the data to be saved (String, Int, Boolean)
     */
    fun savePrimitiveData(key: String, data: Any) {
        runCatching {
            val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            prefs.edit {

                when (data) {
                    is String -> putString(key, data)
                    is Int -> putInt(key, data)
                    is Boolean -> putBoolean(key, data)
                    else -> throw IllegalArgumentException("Unsupported data type")
                }

            }
            Logger.i("SharedPreference", "Saved [$key] :: $data")
        }.onFailure {
            Logger.e("SharedPreference", "Saving [$key] error: ${it.message}")
        }
    }

    /**
     * Fetch primitive data types (String, Int, Boolean) from SharedPreferences
     *
     * @param key the key used to retrieve the data
     * @param defaultValue the default value if data is not found
     * @return the stored data or the default value if not found
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getPrimitiveData(key: String, defaultValue: T): T {
        return runCatching {
            val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            when (defaultValue) {
                is String -> prefs.getString(key, defaultValue) as T
                is Int -> prefs.getInt(key, defaultValue) as T
                is Boolean -> prefs.getBoolean(key, defaultValue) as T
                else -> throw IllegalArgumentException("Unsupported default value type")
            }
        }.getOrElse {
            Logger.e("SharedPreference", "Fetching [$key] error: ${it.message}")
            defaultValue
        }
    }

    /**
     * save generic object to SharedPreferences by converting it into a JSON string
     *
     * @param key key used to store the object in SharedPreferences
     * @param data object to be stored.
     */
    fun <T> saveObjectData(key: String, data: T) {
        runCatching {
            val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)

            /// convert object to json string
            val json = gson.toJson(data)

            /// save json string to shared preferences
            prefs.edit { putString(key, json) }
            Logger.i("SharedPreference", "Saved [$key] :: $json")
        }.onFailure {
            Logger.e("SharedPreference", "Saving [$key] error: ${it.message}")
        }
    }

    /**
     * Fetch typed object from SharedPreferences based on key
     * Reified type parameter used to reference the class at runtime
     *
     * @param key used to retrieve the object from SharedPreferences
     * @return deserialized object of type [T], or null if not found or on failure.
     */
    inline fun <reified T> getObjectData(key: String): T? {
        return getObjectDataInternal(key, T::class.java)
    }

    /**
     * Fetchtyped object from SharedPreferences based on key and class type.
     *
     * @param key key used to retrieve the object from SharedPreferences
     * @param classType class type used for deserialization.
     * @return deserialized object of type [T], or null if not found or on failure.
     */
    fun <T> getObjectDataInternal(key: String, classType: Class<T>): T? {
        return runCatching {
            val sharedPreferences = context
                .getSharedPreferences(key, Context.MODE_PRIVATE)
            val sharedPrefData = sharedPreferences.getString(key, "")
            if (sharedPrefData.isNullOrEmpty()) return null

            val data = gson.fromJson(sharedPrefData, classType)

            Logger.i("SharedPreference", "Fetched [$key] :: $data")

            data
        }.onFailure {
            Logger.e("SharedPreference", "Fetched [$key]: ${it.message}")
            null
        }.getOrNull()
    }

    fun deleteObjectData(key: String) {
        runCatching {
            val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            prefs.edit { remove(key) }
            Logger.i("SharedPreference", "Deleted [$key]")
        }.onFailure {
            Logger.e("SharedPreference", "Deleted [$key]: ${it.message}")
            null
        }
    }

    /**
     * Clear all SharedPreferences entries defined in [SharedPreferenceKeys]
     *
     * Use Kotlin reflection to retrieve all string constants from [SharedPreferenceKeys],
     * and delete each key values from SharedPreferences
     */
    fun clearSharedPref() {
        runCatching {
            /**
             * retrieve all shared preferences keys from SharedPreferenceKeys class
             * using kotlin reflect
             */
            SharedPreferenceKeys::class.members
                .filterIsInstance<KProperty1<*, *>>()
                .forEach {
                    /// retrieved key value from SharedPreferenceKeys class
                    val key = it.getter.call() as? String
                    key?.let {
                        val sharedPreferences =
                            context.getSharedPreferences(it, Context.MODE_PRIVATE)
                        /// delete each value of key
                        sharedPreferences.edit().apply {
                            Logger.i("SharedPreference", "Deleted value from [$key]")
                            remove(it)
                            apply()
                        }
                    }

                }
        }.onFailure {
            Logger.e("SharedPreference", "clearSharedPref error: ${it.message}")
        }
    }
}