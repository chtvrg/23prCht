package com.example.a23prcht

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingPager(navController) // Экран с пагером
        }
        composable("registration") {
            RegistrationScreen(navController) // Экран регистрации
        }
        composable("code_screen") {
            CodeInputScreen(navController) // Экран ввода кода
        }
        composable("CreatePassword") {
            CreatePassword(navController) // Экран ввода кода
        }
        composable("CreateCard") {
            CreateCard(navController) // Экран ввода кода
        }

    }
}