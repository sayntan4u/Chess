package com.example.chess.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

val darkSquare = Color(0xFF779556)
val lightSquare = Color(0xFFEBECD0)
val rowName = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

val selectedColor = Color(0xFFf2f28d)//Color(0xFFefef76)

class OccupiedSquares : ViewModel() {
    var whiteOccupiedSquares by mutableStateOf(mutableListOf(""))
    var blackOccupiedSquares by mutableStateOf(mutableListOf(""))

}

class SquareState (
    piece : Piece = Piece.EMPTY,
    clan : Clan = Clan.EMPTY,
    sqrColor: Color = Color.Transparent,
    row : Int = 0,
    col : Int = 0
) : ViewModel()
{

    var piece by mutableStateOf(piece)
    var clan by mutableStateOf(clan)
    var color by mutableStateOf(sqrColor)
    var row by mutableStateOf(row)
    var col by mutableStateOf(col)
    var isHighlight by mutableStateOf(false)
    var isMoved by mutableStateOf(false)

    val pos by mutableStateOf("${rowName[col]}${8-row}")
    val posRow by mutableStateOf(8-row)
    val posCol by mutableStateOf(rowName[col])

}

enum class Clan{
    BLACK,WHITE,EMPTY
}

enum class Piece{
    R,N,B,K,Q,P,EMPTY
}