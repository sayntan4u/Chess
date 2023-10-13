package com.example.chess.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.R
import com.example.chess.model.ChessModel
import com.example.chess.model.Clan
import com.example.chess.model.Piece
import com.example.chess.model.SquareState
import com.example.chess.model.rowName
import com.example.chess.model.selectedColor

@Composable
fun square(
    sqr : SquareState,
    model : ChessModel,
    setCurrent : (SquareState) -> Unit
){
    var bgColor = if(sqr.isHighlight || sqr.isMoved) selectedColor else sqr.color


    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(bgColor)
            .clickable {
                setCurrent(sqr)
            }
    ){
        //Row number
        if(sqr.col==0){
            Text(
                text = "${8-sqr.row}",
                color = Color(0xFF4c4c4c),
                fontSize = 8.sp,
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp)
            )
        }

        if(sqr.pos in model.availableMoves){
            if(sqr.piece == Piece.EMPTY) {
                Canvas(modifier = Modifier
                    .size(14.dp)
                    .align(Alignment.Center), onDraw = {
                    drawCircle(color = Color(0xFF4c4c4c), alpha = 0.5f)
                })
            }
           else if(sqr.clan != model.previousSquare.clan){
                Canvas(modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.Center), onDraw = {
                    drawCircle(color = Color(0xFF4c4c4c), alpha = 0.5f)
                })
                Canvas(modifier = Modifier
                    .size(37.dp)
                    .align(Alignment.Center), onDraw = {
                    drawCircle(color = sqr.color)
                })

            }

        }

        //Draw pieces

        if(sqr.clan == Clan.BLACK){
            if(sqr.pos in model.occupiedSquares.blackOccupiedSquares){
                if(sqr.piece == Piece.P){
                    Image(
                        painter = painterResource(id = R.drawable.black_pawn),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.R){
                    Image(
                        painter = painterResource(id = R.drawable.black_rook),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.N){
                    Image(
                        painter = painterResource(id = R.drawable.black_knight),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.B){
                    Image(
                        painter = painterResource(id = R.drawable.black_bishop),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.K){
                    Image(
                        painter = painterResource(id = R.drawable.black_king),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.Q){
                    Image(
                        painter = painterResource(id = R.drawable.black_queen),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
            }

        }
        if(sqr.clan == Clan.WHITE){
            if(sqr.pos in model.occupiedSquares.whiteOccupiedSquares){
                if(sqr.piece == Piece.P){
                    Image(
                        painter = painterResource(id = R.drawable.white_pawn),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.R){
                    Image(
                        painter = painterResource(id = R.drawable.white_rook),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.N){
                    Image(
                        painter = painterResource(id = R.drawable.white_knight),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.B){
                    Image(
                        painter = painterResource(id = R.drawable.white_bishop),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.K){
                    Image(
                        painter = painterResource(id = R.drawable.white_king),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(sqr.piece == Piece.Q){
                    Image(
                        painter = painterResource(id = R.drawable.white_queen),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
            }

        }


        //Column names
        if(sqr.row==7) {
            Text(
                text = "${rowName[sqr.col]}",
                color = Color(0xFF4c4c4c),
                fontSize = 8.sp,
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp)
                    .align(Alignment.BottomEnd),

                )
        }
    }
}



/*
@Composable
fun ChessBoxContent(
    state : SquareState,
    occupiedSquares: OccupiedSquares,
    current: SquareState,
    setCurrent : (SquareState) -> Unit,
    reClick : Boolean,
    moves : MutableList<String>,
    movedFrom : String
){
    var bgColor = if(current.pos == state.pos && !reClick)selectedColor else state.color


    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(bgColor)
            .clickable {
                println(state.piece)
                println(state.clan)

                setCurrent(state)
            }
    ){
        //Row number
        if(state.col==0){
            Text(
                text = "${8-state.row}",
                color = Color(0xFF4c4c4c),
                fontSize = 8.sp,
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp)
            )
        }

        if(state.pos in moves){
            if(state.piece == Piece.EMPTY) {
                Canvas(modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.Center), onDraw = {
                    drawCircle(color = Color.DarkGray)
                })
            }else if(state.clan != current.clan){
                Canvas(modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center), onDraw = {
                    drawCircle(color = Color(0xFFff4d4d))
                })
                Canvas(modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center), onDraw = {
                    drawCircle(color = state.color)
                })

            }

        }

        if(state.pos == movedFrom){
            state.clan = Clan.EMPTY
            state.piece = Piece.EMPTY
        }

        //Draw pieces

        if(state.clan == Clan.BLACK){
            if(state.pos in occupiedSquares.blackOccupiedSquares){
                if(state.piece == Piece.P){
                    Image(
                        painter = painterResource(id = R.drawable.black_pawn),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.R){
                    Image(
                        painter = painterResource(id = R.drawable.black_rook),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.N){
                    Image(
                        painter = painterResource(id = R.drawable.black_knight),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.B){
                    Image(
                        painter = painterResource(id = R.drawable.black_bishop),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.K){
                    Image(
                        painter = painterResource(id = R.drawable.black_king),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.Q){
                    Image(
                        painter = painterResource(id = R.drawable.black_queen),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
            }

        }
        if(state.clan == Clan.WHITE){
            if(state.pos in occupiedSquares.whiteOccupiedSquares){
                if(state.piece == Piece.P){
                    Image(
                        painter = painterResource(id = R.drawable.white_pawn),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.R){
                    Image(
                        painter = painterResource(id = R.drawable.white_rook),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.N){
                    Image(
                        painter = painterResource(id = R.drawable.white_knight),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.B){
                    Image(
                        painter = painterResource(id = R.drawable.white_bishop),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.K){
                    Image(
                        painter = painterResource(id = R.drawable.white_king),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
                if(state.piece == Piece.Q){
                    Image(
                        painter = painterResource(id = R.drawable.white_queen),
                        contentDescription = "",
                        modifier = Modifier.padding(3.dp))
                }
            }

        }


        //Column names
        if(state.row==7) {
            Text(
                text = "${rowName[state.col]}",
                color = Color(0xFF4c4c4c),
                fontSize = 8.sp,
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp)
                    .align(Alignment.BottomEnd),

                )
        }


    }
}

 */