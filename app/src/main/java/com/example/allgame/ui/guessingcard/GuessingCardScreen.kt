package com.example.allgame.ui.guessingcard

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.allgame.R
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.allgame.ui.guessingnumber.randomNumber

var viewModel: CardViewModel = CardViewModel();

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GuessingCardScreen(
    name: String,
    navController: NavController,

) {
    val viewModel by remember { mutableStateOf(viewModel) }
    viewModel.loadCards();
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        },

        content = {
            val cards: List<CardModel> by viewModel.getCards().observeAsState(listOf())
            CardsGrid(cards = cards)
        },
        bottomBar =
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 200.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Restart Game",
                    fontSize = 25.sp,
                    color = Color.White,
                    modifier = Modifier
                        .clickable {
                            viewModel.loadCards()
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
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CardsGrid(cards: List<CardModel>) {
    val cardCount = cards.count()
    val columns = 4
    val rows = (cardCount + columns - 1) / columns

    LazyColumn(Modifier.fillMaxWidth()) {
        items(rows) { rowIndex ->
            Row(Modifier.fillMaxWidth()) {
                for (colIndex in 0 until columns) {
                    val cardIndex = rowIndex * columns + colIndex
                    if (cardIndex < cardCount) {
                        CardItem(cards[cardIndex], Color.White)
                    } else {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}



@Composable
fun CardItem(numb: CardModel, color: Color) {
    Box(
        modifier = Modifier
            .padding(all = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(width = 90.dp, height = 150.dp)
                .background(
                    color = Color.Blue.copy(alpha = if (numb.isVisible) 0.4F else 0.0F),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    if (numb.isVisible) {
                        viewModel.updateShowVisibleCard(numb.id)
                    }
                    Log.i("INFO",""+numb.id.toString()+" , "+numb.isVisible.toString()+" , "+numb.char+numb.isSelect.toString())
                }

        ) {
            if (numb.isSelect) {
                Text(
                    text = numb.char,
                    fontSize = 40.sp,
                    color = color
                )
            }
        }
    }
}


