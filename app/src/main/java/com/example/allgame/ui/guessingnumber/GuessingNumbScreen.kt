package com.example.allgame.ui.guessingnumber

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random.Default.nextInt
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.navigation.NavController
import com.example.allgame.R

var randomNumber: Int = nextInt(1,1000)


@Composable
fun GuessingNumbScreen(
    name: String,
    navController: NavController
) {
    var randomNumber by remember { mutableStateOf(randomNumber) }
    var inputNumber by remember { mutableStateOf("") }
    val input = inputNumber.toIntOrNull()
    var hint by remember { mutableStateOf("Let's Play") }
    var count by remember { mutableStateOf(0) }
    var guess by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colors.surface,
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = "Guessing the number between 1 - 1000",
            color = MaterialTheme.colors.surface,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(150.dp))

        EditNumberField(value = inputNumber,
            onValueChange = { inputNumber = it }
        )
        Spacer(Modifier.height(200.dp))

        if (guess) {
            Text(
                text = hint,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.surface,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    count += 1

                    if (input == null) {
                        hint = "\n Let's Play"
                    } else if (input > randomNumber) {
                        hint = "\nYour answer is Higher ! "
                    } else if (input < randomNumber) {
                        hint = "\nYour answer is Lower !"
                    } else {
                        guess = false
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = MaterialTheme.colors.onPrimary
                )
            )
        } else {
            Text(
                text = "Your correct!",
                color = MaterialTheme.colors.surface,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = count,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))
        }
        Button(onClick = {
            count = 0
            guess = true
            inputNumber = ""
            hint = "Let's Play"
            randomNumber = nextInt(1, 1000) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = Color.White)
        ){
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = "restart"
            )
        }
    }


}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            text = "the number of guessing time"
        )},
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() })
    )
}

