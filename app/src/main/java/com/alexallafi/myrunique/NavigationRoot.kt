package com.alexallafi.myrunique

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.alexallafi.auth.presentation.intro.IntroScreenRoot
import com.alexallafi.auth.presentation.login.LoginScreenRoot
import com.alexallafi.auth.presentation.register.RegisterScreenRoot
import com.alexallafi.run.presentation.active_run.ActiveRunScreenRoot
import com.alexallafi.run.presentation.active_run.service.ActiveRunService
import com.alexallafi.run.presentation.run_overview.RunOverviewScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "run" else "auth"
    ) {
        authGraph(navController)
        runGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navHostController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "auth"
    ) {
        composable(route = "intro") {
            IntroScreenRoot(
                onSignUpClick = {
                    navHostController.navigate("register")
                },
                onSignInClick = {
                    navHostController.navigate("login")
                }
            )
        }
        composable(route = "register") {
            RegisterScreenRoot(
                onSignInClick = {
                    navHostController.navigate("login") {
                        popUpTo("register") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navHostController.navigate("login")
                }
            )
        }
        composable("login") {
            LoginScreenRoot(
                onLoginSuccess = {
                    navHostController.navigate("run") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navHostController.navigate("register") {
                        popUpTo("login") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.runGraph(navHostController: NavHostController) {
    navigation(
        startDestination = "run_overview",
        route = "run"
    ) {
        composable("run_overview") {
            RunOverviewScreenRoot(
                onStartRunClick = {
                    navHostController.navigate("active_run")
                }
            )
        }
        composable(
            route = "active_run",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "runique://active_run"
                }
            )) {

            val context = LocalContext.current

            ActiveRunScreenRoot(
                onBack = {
                    navHostController.navigateUp()
                },
                onFinish = {
                    navHostController.navigateUp()
                },
                onServiceToggle = { shouldServiceRun ->
                    if (shouldServiceRun) {
                        context.startService(ActiveRunService.createStartIntent(context, MainActivity::class.java))
                    } else {
                        context.startService(ActiveRunService.createStopIntent(context))
                    }
                }
            )
        }
    }
}