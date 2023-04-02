package com.example.allgame.ui.guessingquiz


import androidx.lifecycle.ViewModel
import com.example.allgame.data.ListOfQuestion
import com.example.allgame.data.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizUiState(
    val score: Int,
    val quizNumber: Int,
    val options: List<String>,
    val currentQuestion: QuizQuestion,
)

class QuizViewModel : ViewModel() {

    private val quizData = ListOfQuestion()
    private var questions = quizData.questions.shuffled()
    private var quizNumber = 0
    private var score = 0
    private val _uiState = MutableStateFlow(initialUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()


    fun resetQuiz() {
        questions = quizData.questions.shuffled()
        quizNumber = 0
        score = 0

        updateUiState()
    }

    fun answerQuestion(answer: String) {
        if (isCorrectAnswer(answer)) {
            score++
        }

        if (hasMoreQuestions()) {
            goToNextQuestion()
        } else {
            endQuiz()
        }
    }

    private fun isCorrectAnswer(answer: String): Boolean {
        return answer == currentQuestion().correctAnswer
    }

    private fun hasMoreQuestions(): Boolean {
        return quizNumber < questions.size
    }

    private fun currentQuestion(): QuizQuestion {
        return questions[quizNumber]
    }

    private fun goToNextQuestion() {
        quizNumber++
        updateUiState()
    }

    private fun endQuiz() {
        _uiState.value = QuizUiState(
            currentQuestion = QuizQuestion("", listOf(), correctAnswer = ""),
            options = listOf(),
            score = score,
            quizNumber = quizNumber
        )
    }

    private fun initialUiState(): QuizUiState {
        return QuizUiState(
            currentQuestion = currentQuestion(),
            options = currentQuestion().options.shuffled(),
            score = score,
            quizNumber = quizNumber + 1
        )
    }

    private fun updateUiState() {
        _uiState.value = initialUiState()
    }
}
