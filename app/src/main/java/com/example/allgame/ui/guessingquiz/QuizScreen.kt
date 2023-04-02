package com.example.allgame.ui.guessingquiz

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.allgame.data.QuizQuestion


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuizScreen(
    name: String,
    quizViewModel: QuizViewModel,
    restartquiz: () -> Unit,
    quitquiz: () -> Unit,
    navController: NavController
) {

    val uiState by quizViewModel.uiState.collectAsState()

    Scaffold(

        topBar = { TopAppBar(title = { Text("CN333 ASIGNMENT2") }) },
        content = {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                if (uiState.quizNumber == 11) {
                    FinalScreen(score = uiState.score, restartquiz = restartquiz, quitquiz = quitquiz)

                } else {
                    QuestionScreen(
                        question = uiState.currentQuestion,
                        options = uiState.options,
                        score = uiState.score,
                        quizNum = uiState.quizNumber,
                        correctAnswer = uiState.options,
                        SelectedAnswer = quizViewModel::answerQuestion

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
    SelectedAnswer: (String) -> Unit
) {

    Column(
        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
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
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
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
fun FinalScreen(score: Int, restartquiz: () -> Unit, quitquiz: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(100.dp),
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

        Button(onClick = restartquiz,
            modifier = Modifier.width(IntrinsicSize.Max)) {
            Text(
                text = "RESTART",
                fontSize = 22.sp
            )
        }

        Button(onClick = quitquiz,
            modifier = Modifier.width(IntrinsicSize.Max)) {
            Text(
                text = "QUIT A QUIZ",
                fontSize = 22.sp)
        }
    }
}

