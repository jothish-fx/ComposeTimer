package com.fx.composetimer.data.utils

import android.content.Context
import android.content.res.Configuration


val Context.isDarkTheme
    get() = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
