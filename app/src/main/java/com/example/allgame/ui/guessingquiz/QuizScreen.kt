package com.example.allgame.ui.guessingquiz

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.allgame.data.QuizQuestion
import kotlin.reflect.KFunction1


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuizScreen(
    name: String,
    quizViewModel: QuizViewModel,
    restartquiz: KFunction1<QuizViewModel, Unit>,
    quitquiz: () -> Unit,
    navController: NavController
) {

    val uiState by quizViewModel.uiState.collectAsState()

    Scaffold(

        topBar = { TopAppBar(title = { Text("CN333 ASIGNMENT2") }) },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {

                if (uiState.quizNumber == 11) {
                    FinalScreen(score = uiState.score, restartquiz = restartquiz, quitquiz = quitquiz, navController = navController)

                } else {
                    QuestionScreen(
                        question = uiState.currentQuestion,
                        options = uiState.options,
                        score = uiState.score,
                        quizNum = uiState.quizNumber,
                        correctAnswer = uiState.options,
                        SelectedAnswer = quizViewModel::answerQuestion,
                        navController = navController
                    )
                }
            }
        }
    )
}

@Composable
fun QuestionScreen(
    score: Int,
    quizNum: Int,
    question: QuizQuestion,
    options: List<String>,
    correctAnswer: List<String>,
    navController: NavController,
    SelectedAnswer: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Question No. $quizNum",
            fontSize = 25.sp
        )


        Text(
            text = question.question,
            modifier = Modifier.padding(bottom = 15.dp),
            fontSize = 22.sp)

        options.forEach { choice ->
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                onClick = { SelectedAnswer(choice) }
            ) {
                Text(
                    text = choice,
                    fontSize = 20.sp)
            }
        }

        Text(
            text = "Your score: $score out of 10",
            fontSize = 24.sp)


    }

}


@Composable
fun FinalScreen(score: Int, restartquiz: KFunction1<QuizViewModel, Unit>, quitquiz: () -> Unit, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Your score is"
            , modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 26.sp
        )

        Text(text = " $score out of 10",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 26.sp
        )

        Text(
            text = "Restart",
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier
                .clickable {
                    restartquiz
                }
                .padding(1.dp)
                .background(Color.Magenta.copy(0.7F), RoundedCornerShape(25))
        )

        Text(
            text = "Back",
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .padding(1.dp)
                .background(Color.Magenta.copy(0.7F), RoundedCornerShape(25))
        )
    }
}
@Composable
private fun ColumnScope.Button(restartquiz: KFunction1<QuizViewModel, Unit>, modifier: Modifier, content: RowScope.() -> Unit) {
    Text(
        text = "Restart",
        fontSize = 25.sp,
        color = Color.White,
        modifier = Modifier
            .clickable {
                restartquiz
            }
            .padding(1.dp)
            .background(Color.Magenta.copy(0.7F), RoundedCornerShape(25))
    )
}

