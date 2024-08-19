package com.alexallafi.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick : AnalyticsAction
}