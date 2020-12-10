package net.altunyay.myunittestapp.utils

import android.util.Patterns

fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid(): Boolean = this.length > 5