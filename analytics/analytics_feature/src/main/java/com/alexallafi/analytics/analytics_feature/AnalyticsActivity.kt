package com.alexallafi.analytics.analytics_feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexallafi.analytics.presentation.AnalyticsDashboardScreenRot
import com.alexallafi.analytics.presentation.di.analyticsPresentationModule
import com.alexallafi.data.di.analyticsModule
import com.alexallafi.presentation.designsystem.MyRuniqueTheme
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.core.context.loadKoinModules

class AnalyticsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(
            listOf(
                analyticsModule,
                analyticsPresentationModule
            )
        )
        SplitCompat.installActivity(this)

        setContent {
            MyRuniqueTheme {
                val navController = rememberNavController()
                NavHost(
                    navController =navController,
                    startDestination = "analytics_dashboard",
                ) {
                    composable("analytics_dashboard") {
                        AnalyticsDashboardScreenRot(
                            onBackClick = { finish() }
                        )
                    }
                }
            }
        }
    }
}