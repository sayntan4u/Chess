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
import androidx.compose.ui.layout.ContentScale
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

