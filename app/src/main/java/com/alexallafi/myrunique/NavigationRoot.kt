package com.alexallafi.myrunique

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alexallafi.auth.presentation.intro.IntroScreenRoot
import com.alexallafi.auth.presentation.register.RegisterScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        authGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navHostController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "auth"
    ) {
        composable(
            route = "intro"
        ) {
            IntroScreenRoot(
                onSignUpClick = {
                    navHostController.navigate("register") {
                        popUpTo("register") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSignInClick = {
                    navHostController.navigate("login")
                }
            )
        }
        composable(route = "register") {
            RegisterScreenRoot(
                onSignInClick = { navHostController.navigate("login") },
                onSuccessfulRegistration = { navHostController.navigate("login")
                }
            )
        }
    }
}