package com.example.allgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allgame.ui.guessingcard.CardViewModel
import com.example.allgame.ui.guessingcard.GuessingCardScreen
import com.example.allgame.ui.guessingnumber.GuessingNumbScreen
import com.example.allgame.ui.guessingquiz.QuizScreen
import com.example.allgame.ui.guessingquiz.QuizViewModel
import com.example.allgame.ui.theme.AllgameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllgameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) {
                            HomeScreen(navController = navController)
                        }
                        composable("Guessing Number") {
                            GuessingNumbScreen(name = "Guessing Number", navController = navController)
                        }
                        composable("Guessing Quiz") {
                            QuizScreen(name = "Guessing Quiz", quizViewModel = QuizViewModel(), navController = navController)
                        }
                        composable("Guessing Cards") {
                            GuessingCardScreen(name = "Guessing Cards", cardviewModel = CardViewModel(), navController = navController)
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    Home,
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { navController.navigate("Guessing Number") },
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                "Guessing Number",
                fontSize = 20.sp
            )
        }
        Button(
            onClick = { navController.navigate("Guessing Quiz") },
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                "Guessing Quiz",
                fontSize = 20.sp
            )
        }
        Button(onClick = { navController.navigate("Guessing Cards") },
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                "Guessing Cards",
                fontSize = 20.sp)
        }
    }
}