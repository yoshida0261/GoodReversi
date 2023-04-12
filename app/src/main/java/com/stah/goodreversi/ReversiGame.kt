package com.stah.goodreversi

import androidx.compose.ui.graphics.Color

class ReversiGame {
    private val board = Board()

    fun showCurrentPlayer(): String {
        return if (currentPlayer == Color.Black) "Black" else "White"
    }

    var currentPlayer = Color.Black
        private set

    fun getPiece(x: Int, y: Int): Color {
        return board.getPiece(x, y)
    }

    fun setPiece(x: Int, y: Int, color: Color) {
        board.setPiece(x, y, color)
    }

    fun switchTurn() {
        currentPlayer = if (currentPlayer == Color.Black) Color.White else Color.Black
    }
}
