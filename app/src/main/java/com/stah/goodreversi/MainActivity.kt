package com.stah.goodreversi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stah.goodreversi.ui.theme.GoodReversiTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoodReversiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReversiApp()
                }
            }
        }
    }
}

@Composable
fun ReversiCell(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent
) {
    /*
    val scale = animateFloatAsState(
        if (color == Color.Transparent) 1f else 0.5f,
        animationSpec = tween(300), label = ""
    )
     */
    Box(modifier = modifier.size(40.dp)) {
        if (color != Color.Transparent) {
            AnimatedVisibility(visible = color != Color.Transparent) {
                Surface(
                    modifier = Modifier
                        //.scale(scale.value)
                        .size(40.dp)
                        .border(1.dp, Color.Black)
                        .size(36.dp)
                        .align(Alignment.Center)
                        .border(2.dp, Color.Black, CircleShape),
                    shape = CircleShape,
                    color = color
                ) {}
            }
        } else {
            Surface(
                modifier = modifier
                    .size(40.dp)
                    .border(1.dp, Color.Black)
                    .clickable { onClick.invoke() },
                color = color
            ) {}
        }
    }
}

@Composable
fun ReversiBoard() {
    val game = remember { ReversiGame() }
    Column {
        // 現在の手番を表示
        Text(text = "Current turn: ${if (game.currentPlayer == Color.Black) "Black" else "White"}", fontSize = 24.sp)

        for (y in 0 until 8) {
            Row {
                for (x in 0 until 8) {
                    ReversiCell(
                        color = game.getPiece(x, y),
                        onClick = {
                            game.setPiece(x, y, game.currentPlayer)
                            game.switchTurn()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ReversiApp() {
    ReversiBoard()
}

@Preview(showBackground = true)
@Composable
fun ReversiCellPreview() {
    var cellColor by remember { mutableStateOf(Color.Transparent) }

    //ReversiCell(color = cellColor)

    LaunchedEffect(Unit) {
        launch {
            while (true) {
                delay(1500)
                cellColor = if (cellColor == Color.Transparent) Color.White else Color.Transparent
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoodReversiTheme {
        ReversiApp()
    }
}