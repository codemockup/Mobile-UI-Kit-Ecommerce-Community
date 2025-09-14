package com.codemockup.ecommercecommunity.utils.extensions

import com.codemockup.ecommercecommunity.common.constants.NavigationConst

/// function to add arguments template to a route
fun String.withArgs(): String =
    "$this?${NavigationConst.DEFAULT_ARGS}={${NavigationConst.DEFAULT_ARGS}}"