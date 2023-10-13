package com.example.chess.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.chess.R
import com.example.chess.model.ChessModel
import com.example.chess.model.Clan
import com.example.chess.model.OccupiedSquares
import com.example.chess.model.Piece
import com.example.chess.model.SquareState
import com.example.chess.model.darkSquare
import com.example.chess.model.lightSquare
import com.example.chess.model.rowName
import com.example.chess.model.selectedColor

@Composable
fun board(model : ChessModel){

    var x = 0
    Column(
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
    ) {
        for(i in 0 until 8){
            Row {
                for (j in 0 until 8) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(model.boardState[x].color)
                    ){

                        square(
                            sqr = model.boardState[x],
                            model = model,
                            setCurrent = {

                                model.clearSquareMoved()

                                if(model.isSquareHighlighted() && it.pos != model.previousSquare.pos && it.clan != model.previousSquare.clan && it.piece != model.previousSquare.piece){
                                    model.switchSquareMoved(model.previousSquare.pos)
                                }


                                println("clicked :")
                                println(it.piece)
                                println(it.clan)
                                println(it.pos)
                                println("-------------------")
                                println("previous piece : " + model.previousSquare.piece)
                                println("previous clan : " + model.previousSquare.clan)
                                println (" previous pos : " + model.previousSquare.pos)
                                println("--------------------")

                                if(it.piece != Piece.EMPTY){
                                    model.switchHighlight(it.pos)
                                }
                                else{
                                    model.clearAllHighlight()
                                }


                                //Move Piece
                                if(model.currentSide == model.previousSquare.clan){
                                    if(model.previousSquare.pos != it.pos){
                                        if(it.pos in model.availableMoves){
                                            model.movePiece(model.previousSquare.pos,it.pos)
                                            model.clearAvailableMove()
                                            model.clearAllHighlight()
                                            model.switchSquareMoved(it.pos)
                                        }
                                    }
                                }




                                if(model.isSquareHighlighted()){
                                    model.checkAvailableMove(it)
                                }
                                else{
                                    model.clearAvailableMove()
                                }

                                model.previousSquare = it

                                println("previous piece : " + model.previousSquare.piece)
                                println("previous clan : " + model.previousSquare.clan)
                                println (" previous pos : " + model.previousSquare.pos)
                                println("--------------------")

                                println(model.availableMoves)
                                println(" White : " + model.occupiedSquares.whiteOccupiedSquares)
                                println(" Black : " + model.occupiedSquares.blackOccupiedSquares)

                                println("________Board_________")
                                var i = 0
                                for( sqr in model.boardState){

                                    if(sqr.piece == Piece.EMPTY){
                                        print(".  ")
                                    }
                                    else{
                                        if(sqr.clan == Clan.BLACK){
                                            print(sqr.piece.toString().toUpperCase() + "  ")
                                        }
                                        if(sqr.clan == Clan.WHITE){
                                            print(sqr.piece.toString().toLowerCase() + "  ")
                                        }

                                    }


                                    if(i == 7){
                                        i = -1
                                        println()
                                    }
                                    i += 1

                                }

                                println("______________________")
                            }
                        )
                        x += 1
                    }
                }
            }
        }
    }

}




