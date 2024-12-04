package com.example.a23prcht

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

// Данные для страницы
data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun OnboardingScreen(page: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Изменение: используем данные из OnboardingPage
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp) // Adjust size
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPager(navController: NavController) {
    val pagerState = rememberPagerState()

    // Data for screens
    val pages = listOf(
        OnboardingPage(
            title = "Анализы",
            description = "Экспресс сбор и получение проб",
            imageRes = R.drawable.kart1
        ),
        OnboardingPage(
            title = "Уведомления",
            description = "Вы быстро узнаете о результатах",
            imageRes = R.drawable.kart2
        ),
        OnboardingPage(
            title = "Мониторинг",
            description = "Наши врачи всегда наблюдают за вашими показателями здоровья",
            imageRes = R.drawable.kart3
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = if (pagerState.currentPage == pages.lastIndex) "Завершить" else "Пропустить",
            color = Color(0xFF2196F3),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(25.dp)
                .clickable {
                    if (pagerState.currentPage == pages.lastIndex) {
                        // Переход на экран регистрации после завершения
                        navController.navigate("registration")
                    } else {
                        // Переход на экран регистрации после нажатия "Пропустить"
                        navController.navigate("registration")
                    }
                }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
        ) {
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingScreen(page = pages[page]) // Передаем OnboardingPage
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(30.dp),
                activeColor = Color(0xFF2196F3),
                inactiveColor = Color.Gray
            )
        }
    }
}

