package com.codemockup.ecommercecommunity.features.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home_page")
    data object BeanAdd : Screen("bean_add_page")
}