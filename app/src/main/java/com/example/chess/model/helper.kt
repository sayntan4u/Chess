package com.example.chess.model

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

val darkSquare = Color(0xFF779556)
val lightSquare = Color(0xFFEBECD0)

val notActiveTimerColor = Color(0xFF262626)
val activeTimerColor = Color(0xFF007e3f)

val rowName = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

val selectedColor = Color(0xFFf2f28d)//Color(0xFFefef76)

class OccupiedSquares : ViewModel() {
    var whiteOccupiedSquares by mutableStateOf(mutableListOf(""))
    var blackOccupiedSquares by mutableStateOf(mutableListOf(""))

}

class CapturedPieces : ViewModel() {
    var byWhiteCapturedPieces by mutableStateOf(mutableListOf(Piece.EMPTY))
    var byBlackCapturedPieces by mutableStateOf(mutableListOf(Piece.EMPTY))
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

val chessPieceUnicodeWhite = mapOf<Piece,String>(
    Piece.P to "\u2659",
    Piece.R to "\u2656",
    Piece.N to "\u2658",
    Piece.B to "\u2657",
    Piece.K to "\u2654",
    Piece.Q to "\u2655",
)

val chessPieceUnicodeBlack = mapOf<Piece,String>(
    Piece.P to "\u265F",
    Piece.R to "\u265C",
    Piece.N to "\u265E",
    Piece.B to "\u265D",
    Piece.K to "\u265A",
    Piece.Q to "\u265B",
)























