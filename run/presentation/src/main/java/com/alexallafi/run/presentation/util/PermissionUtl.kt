package com.alexallafi.run.presentation.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat

fun ComponentActivity.shouldShowLocationPermissionRationale(): Boolean {
    return shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)
}

fun ComponentActivity.shouldShowNotificationPermissionRationale(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)
    } else false
}

private fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasLocationPermission(): Boolean {
    return hasPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
}

fun Context.hasNotificationPermission(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        hasPermission(android.Manifest.permission.POST_NOTIFICATIONS)
    } else true
}

