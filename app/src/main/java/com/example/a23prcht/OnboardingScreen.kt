package com.example.a23prcht

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Pager
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingScreen(page = pages[page])
        }

        // Indicators
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = Color(0xFF4CAF50), // Green for active indicator
            inactiveColor = Color.Gray
        )

        // Skip or Finish button
        TextButton(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    navController.navigate("registration")
                } else {
                    navController.navigate("registration")
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = if (pagerState.currentPage == pages.lastIndex) "Завершить" else "Пропустить",
                color = Color(0xFF4CAF50),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun OnboardingScreen(page: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Icon or Image
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp) // Adjust size
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

// Data class for each page
data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)