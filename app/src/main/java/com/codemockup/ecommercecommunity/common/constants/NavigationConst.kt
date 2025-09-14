package com.codemockup.ecommercecommunity.common.constants

import androidx.navigation.NavType
import androidx.navigation.navArgument

object NavigationConst {
    const val DEFAULT_ARGS = "args"

    /// provide basic navigation arguments
    val argsTemplate = listOf(
        navArgument(DEFAULT_ARGS) {
            type = NavType.StringType
            nullable = true
        },
    )
}