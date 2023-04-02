package com.example.allgame.data

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

class ListOfQuestion {
    val questions = listOf(
        QuizQuestion(
            "Which country has a unicorn as its national animal?",
            listOf("Finland", "Scotland", "Thailand", "Denmark"),
            "Scotland"
        ),
        QuizQuestion(
            "What country has the longest coastline in the world?",
            listOf("Canada", "USA", "Chile", "South Africa"),
            "Canada"
        ),
        QuizQuestion(
            "What is the smallest country in the world?",
            listOf("Slovakia", "Singapore", "Papua New Guinea", "Vatican City"),
            "Vatican City"
        ),
        QuizQuestion(
            "What country is also the largest island in the world?",
            listOf("Greenland", "Australia", "Iceland", "Singapore"),
            "Greenland"
        ),
        QuizQuestion(
            "Which country in the European Union has the biggest population?",
            listOf("Germany", "France", "Italy", "Greece"),
            "Germany"
        ),
        QuizQuestion(
            "What is the hottest country in the world?",
            listOf("India", "Nepal", "Burkina Faso", "Thailand"),
            "Burkina Faso"
        ),
        QuizQuestion(
            "Which country has the most islands in the world?",
            listOf("Japan", "Sweden", "Finland", "Philippines"),
            "Sweden"
        ),
        QuizQuestion(
            "Where is the Gobi Desert located?",
            listOf("Mongolia", "Somalia", "Nigeria", "Thailand"),
            "Mongolia"
        ),
        QuizQuestion(
            "India, China, and the U.S. are the most populous counties in the world.  Which country is the next most populous?",
            listOf("Brazil", "Mexico", "Russia", "Indonesia"),
            "Indonesia"
        ),
        QuizQuestion(
            "What is the name of the longest river in Africa?",
            listOf("Amazon", "Nile", "Chao Phraya", "Bang Pakong"),
            "Nile"
        ),
        QuizQuestion(
            "What is the currency of thailand",
            listOf("bath", "baht", "bard", "bat"),
            "baht"
        ),
    )
}