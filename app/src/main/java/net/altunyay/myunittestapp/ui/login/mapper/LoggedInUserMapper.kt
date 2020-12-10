package net.altunyay.myunittestapp.ui.login.mapper

import net.altunyay.myunittestapp.data.model.LoggedInUser
import net.altunyay.myunittestapp.ui.login.model.LoggedInUserView

fun LoggedInUser.toLoggedInUserView(): LoggedInUserView =
    LoggedInUserView(displayName = displayName)