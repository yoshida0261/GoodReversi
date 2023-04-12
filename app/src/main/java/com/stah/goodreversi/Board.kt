package com.stah.goodreversi

import androidx.compose.ui.graphics.Color

class Board {
    private val size = 8
    private val board: Array<Array<Color>> =
        Array(size) { y -> Array(size) { x -> initialPieceColor(x, y) } }

    fun getPiece(x: Int, y: Int): Color {
        return board[y][x]
    }

    fun setPiece(x: Int, y: Int, color: Color) {
        board[y][x] = color
    }

    private fun initialPieceColor(x: Int, y: Int): Color {
        return when {
            x == 3 && y == 3 || x == 4 && y == 4 -> Color.White
            x == 3 && y == 4 || x == 4 && y == 3 -> Color.Black
            else -> Color.Transparent
        }
    }
}
