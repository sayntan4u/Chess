package com.example.chess.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ChessModel : ViewModel(){

    var boardState by mutableStateOf(mutableListOf(SquareState()))
    var occupiedSquares by mutableStateOf(OccupiedSquares())
    var capturedPieces by mutableStateOf(CapturedPieces())
    var availableMoves by mutableStateOf(mutableListOf(""))
    var previousSquare by mutableStateOf(SquareState())

    var currentSide by mutableStateOf(Clan.WHITE)

    var whiteKingPos by mutableStateOf("")
    var blackKingPos by mutableStateOf("")



    init{
        boardState.clear()
        occupiedSquares.whiteOccupiedSquares.clear()
        occupiedSquares.blackOccupiedSquares.clear()

        capturedPieces.byWhiteCapturedPieces.clear()
        capturedPieces.byBlackCapturedPieces.clear()

        //Setup the initial pieces
        for (i in 0 until 8) {
            for (j in 0 until 8) {
                val isLightSquare = i % 2 == j % 2
                val squareColor = if (isLightSquare) lightSquare else darkSquare
                var b by mutableStateOf(SquareState(row = i, col = j, sqrColor = squareColor))

                if(i==0){
                    b.clan = Clan.BLACK
                    occupiedSquares.blackOccupiedSquares.add(b.pos)

                    if(j==0 || j==7){
                        b.piece = Piece.R
                    }
                    if(j==1 || j==6){
                        b.piece = Piece.N
                    }
                    if(j==2 || j==5){
                        b.piece = Piece.B
                    }
                    if(j==3){
                        b.piece = Piece.Q
                    }
                    if(j==4){
                        b.piece = Piece.K
                        blackKingPos = b.pos
                    }
                }
                if(i==1){
                    b.clan = Clan.BLACK
                    b.piece = Piece.P
                    occupiedSquares.blackOccupiedSquares.add(b.pos)
                }

                if(i==6){
                    b.clan = Clan.WHITE
                    b.piece = Piece.P
                    occupiedSquares.whiteOccupiedSquares.add(b.pos)
                }
                if(i==7){
                    b.clan = Clan.WHITE
                    occupiedSquares.whiteOccupiedSquares.add(b.pos)
                    if(j==0 || j==7){
                        b.piece = Piece.R
                    }
                    if(j==1 || j==6){
                        b.piece = Piece.N
                    }
                    if(j==2 || j==5){
                        b.piece = Piece.B
                    }
                    if(j==3){
                        b.piece = Piece.Q
                    }
                    if(j==4){
                        b.piece = Piece.K
                        whiteKingPos = b.pos
                    }
                }

               boardState.add(b)

            }
        }

    }

    fun clearSquareMoved(){
        for(sqr in boardState){
            sqr.isMoved = false
        }
    }

    fun isSquareMoved() : Boolean{
        for(sqr in boardState) {
            if(sqr.isMoved){
                return true
            }
        }
        return false
    }

    fun switchSquareMoved(pos : String){
        for(sqr in boardState){
            if(sqr.pos == pos){
                sqr.isMoved = !sqr.isMoved
                break
            }
        }
    }

    fun checkAvailableMove(state : SquareState) {

        var moves = mutableListOf<String>()
        var move = ""

        //check pawn moves
        if(state.piece == Piece.P){

            if(state.clan == Clan.WHITE) {

                //straight move
                if (state.posRow == 2) {
                    move = "${state.posCol}${(state.posRow.toInt() + 1)}"

                    var emptySquare = true
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece != Piece.EMPTY){
                            emptySquare = false
                            break
                        }
                    }
                    if(emptySquare){
                        moves.add(move)
                        move = "${state.posCol}${(state.posRow.toInt() + 2)}"

                        emptySquare = true
                        for (sqr in boardState){
                            if(sqr.pos == move && sqr.piece != Piece.EMPTY){
                                emptySquare = false
                                break
                            }
                        }
                        if(emptySquare){
                            moves.add(move)
                        }
                    }


                }
                else {
                    move = "${state.posCol}${(state.posRow.toInt() + 1)}"

                    var emptySquare = true
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece != Piece.EMPTY){
                            emptySquare = false
                            break
                        }
                    }
                    if(emptySquare){
                        moves.add(move)
                    }
                }

                //diagonal left check
                if(state.posCol > 'a'){
                    move = "${state.posCol - 1}${(state.posRow.toInt() + 1)}"

                    var enemySquare = false
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece!= Piece.EMPTY && sqr.clan == Clan.BLACK){
                            enemySquare = true
                            break
                        }
                    }
                    if(enemySquare){
                        moves.add(move)
                    }
                }

                //diagonal right check
                if(state.posCol < 'h'){
                    move = "${state.posCol + 1}${(state.posRow.toInt() + 1)}"

                    var enemySquare = false
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece!= Piece.EMPTY && sqr.clan == Clan.BLACK){
                            enemySquare = true
                            break
                        }
                    }
                    if(enemySquare){
                        moves.add(move)
                    }
                }



            }

            if(state.clan == Clan.BLACK) {
                if (state.posRow == 7) {
                    move = "${state.posCol}${(state.posRow.toInt() - 1)}"

                    var emptySquare = true
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece != Piece.EMPTY){
                            emptySquare = false
                            break
                        }
                    }
                    if(emptySquare){
                        moves.add(move)
                        move = "${state.posCol}${(state.posRow.toInt() - 2)}"

                        emptySquare = true
                        for (sqr in boardState){
                            if(sqr.pos == move && sqr.piece != Piece.EMPTY){
                                emptySquare = false
                                break
                            }
                        }
                        if(emptySquare){
                            moves.add(move)
                    }


                    }
                }
                else {
                    move = "${state.posCol}${(state.posRow.toInt() - 1)}"

                    var emptySquare = true
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece != Piece.EMPTY){
                            emptySquare = false
                            break
                        }
                    }
                    if(emptySquare){
                        moves.add(move)
                    }
                }

                //diagonal left check
                if(state.posCol > 'a'){
                    move = "${state.posCol - 1}${(state.posRow.toInt() - 1)}"

                    var enemySquare = false
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece!= Piece.EMPTY && sqr.clan == Clan.WHITE){
                            enemySquare = true
                            break
                        }
                    }
                    if(enemySquare){
                        moves.add(move)
                    }
                }

                //diagonal right check
                if(state.posCol < 'h'){
                    move = "${state.posCol + 1}${(state.posRow.toInt() - 1)}"

                    var enemySquare = false
                    for (sqr in boardState){
                        if(sqr.pos == move && sqr.piece!= Piece.EMPTY && sqr.clan == Clan.WHITE){
                            enemySquare = true
                            break
                        }
                    }
                    if(enemySquare){
                        moves.add(move)
                    }
                }
            }
        }

        //check Knight moves
        if(state.piece == Piece.N){
            var curRow = state.posRow
            var curCol = state.posCol

            //left
            if(curCol > 'b'){
                if((state.posRow + 1) < 9)
                {
                    move = "${state.posCol - 2}${state.posRow + 1}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){

                        }
                        else{
                            moves.add(move)
                        }
                    }

                }
                if((state.posRow - 1) > 0){
                    move = "${state.posCol - 2}${state.posRow - 1}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){

                        }
                        else{
                            moves.add(move)
                        }
                    }
                }
            }


            //right
            if(curCol < 'g'){
                if((state.posRow + 1) < 9) {
                    move = "${state.posCol + 2}${state.posRow + 1}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){

                        }
                        else{
                            moves.add(move)
                        }
                    }
                }
                if((state.posRow - 1) > 0) {
                    move = "${state.posCol + 2}${state.posRow - 1}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){

                        }
                        else{
                            moves.add(move)
                        }
                    }
                }
            }


            //Up
            if(curRow < 7){
                move = "${state.posCol - 1}${state.posRow + 2}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){

                    }
                    else{
                        moves.add(move)
                    }
                }

                move = "${state.posCol + 1}${state.posRow + 2}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){

                    }
                    else{
                        moves.add(move)
                    }
                }

            }


            //Down
            if(curRow > 2) {
                move = "${state.posCol - 1}${state.posRow - 2}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){

                    }
                    else{
                        moves.add(move)
                    }
                }

                move = "${state.posCol + 1}${state.posRow - 2}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){

                    }
                    else{
                        moves.add(move)
                    }
                }
            }


        }

        //check Bishop moves
        if(state.piece == Piece.B){
            var curRow = state.posRow
            var curCol = state.posCol

            //top left move
            var i = curCol
            var j = curRow
            while(i > 'a'){
                i--
                j++
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }


            }

            //top right move
            i = curCol
            j = curRow
            while(i < 'h'){
                i++
                j++
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }

            }

            //bottom right move
            i = curCol
            j = curRow
            while(j > 1){
                i++
                j--
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
            }

            //bottom left move
            i = curCol
            j = curRow
            while(j > 1){
                i--
                j--
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
            }

        }

        //check Rook moves
        if(state.piece == Piece.R){
            var curRow = state.posRow
            var curCol = state.posCol

            var i = curCol
            //left move
            while(i > 'a'){
                i--
                move = "${i}${(state.posRow.toInt())}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }


            }

            i=curCol
            //right move
            while(i < 'h'){
                i++
                move = "${i}${(state.posRow.toInt())}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }

            }

            //up move
            var j=curRow
            while(j < 8){
                j++
                move = "${state.posCol}${(j)}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }

            }

            //down move
            j=curRow
            while(j>1){
                j--
                move = "${state.posCol}${(j)}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
            }


        }

        //check King moves
        if(state.piece == Piece.K) {
            var curRow = state.posRow
            var curCol = state.posCol

            //left move
            if(curCol > 'a'){
                move = "${state.posCol - 1}${(state.posRow.toInt())}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                    }
                    else{
                        moves.add(move)
                    }
                }

                if(curRow > 1){
                    move = "${state.posCol - 1}${(state.posRow.toInt() - 1)}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                        }
                        else{
                            moves.add(move)
                        }
                    }
                }
                if(curRow < 8){
                    move = "${state.posCol - 1}${(state.posRow.toInt() + 1)}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                        }
                        else{
                            moves.add(move)
                        }
                    }
                }
            }

            //right move
            if(curCol < 'h'){
                move = "${state.posCol + 1}${(state.posRow.toInt())}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                    }
                    else{
                        moves.add(move)
                    }
                }

                if(curRow > 1){
                    move = "${state.posCol + 1}${(state.posRow.toInt() - 1)}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                        }
                        else{
                            moves.add(move)
                        }
                    }
                }
                if(curRow < 8){
                    move = "${state.posCol + 1}${(state.posRow.toInt() + 1)}"
                    if(state.clan == Clan.WHITE){
                        if(move in occupiedSquares.whiteOccupiedSquares){

                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                            moves.add(move)

                        }
                        else{
                            moves.add(move)
                        }
                    }
                    if(state.clan == Clan.BLACK){
                        if(move in occupiedSquares.whiteOccupiedSquares){
                            moves.add(move)
                        }
                        else if(move in occupiedSquares.blackOccupiedSquares){
                        }
                        else{
                            moves.add(move)
                        }
                    }
                }
            }

            //top move
            if(curRow < 8){
                move = "${state.posCol}${(state.posRow.toInt() + 1)}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                    }
                    else{
                        moves.add(move)
                    }
                }
            }

            //bottom move
            if(curRow > 1){
                move = "${state.posCol}${(state.posRow.toInt() - 1)}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){

                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)

                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                    }
                    else{
                        moves.add(move)
                    }
                }
            }
        }

        //check Queen moves
        if(state.piece == Piece.Q){
            var curRow = state.posRow
            var curCol = state.posCol

            var i = curCol
            //left move
            while(i > 'a'){
                i--
                move = "${i}${(state.posRow.toInt())}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }


            }

            i=curCol
            //right move
            while(i < 'h'){
                i++
                move = "${i}${(state.posRow.toInt())}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }

            }

            //up move
            var j=curRow
            while(j < 8){
                j++
                move = "${state.posCol}${(j)}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }

            }

            //down move
            j=curRow
            while(j>1){
                j--
                move = "${state.posCol}${(j)}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
            }

            //top left move
            i = curCol
            j = curRow
            while(i > 'a'){
                i--
                j++
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }


            }

            //top right move
            i = curCol
            j = curRow
            while(i < 'h'){
                i++
                j++
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }

            }

            //bottom right move
            i = curCol
            j = curRow
            while(j > 1){
                i++
                j--
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
            }

            //bottom left move
            i = curCol
            j = curRow
            while(j > 1){
                i--
                j--
                move = "${i}${j}"
                if(state.clan == Clan.WHITE){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
                if(state.clan == Clan.BLACK){
                    if(move in occupiedSquares.whiteOccupiedSquares){
                        moves.add(move)
                        break
                    }
                    else if(move in occupiedSquares.blackOccupiedSquares){
                        break
                    }
                    else{
                        moves.add(move)
                    }
                }
            }


        }

        availableMoves = moves

    }

    fun clearAvailableMove(){
        availableMoves = mutableListOf("")
    }

    fun getSquareAt(pos : String) : SquareState{
        for (sqr in boardState) {
            if (sqr.pos == pos) {
                return sqr
                break
            }
        }
        return SquareState()
    }

    fun setSquareAt(pos : String, s : SquareState){
        for (sqr in boardState) {
            if (sqr.pos == pos) {
                sqr.piece = s.piece
                sqr.clan = s.clan
                break
            }
        }

    }

    fun setEmptySquareAt(pos : String){
        for(sqr in boardState){
            if(sqr.pos == pos){
                sqr.piece = Piece.EMPTY
                sqr.clan = Clan.EMPTY
            }
        }
    }

    fun isSquareHighlighted() : Boolean{
        for(sqr in boardState) {
            if(sqr.isHighlight){
                return true
            }
        }
        return false
    }

    fun switchHighlight(pos : String){

        for(sqr in boardState){

            if(sqr.pos == pos){
                sqr.isHighlight = !sqr.isHighlight
            }
            else{
                sqr.isHighlight = false
            }
        }
    }

    fun clearAllHighlight(){
        for (sqr in boardState){
            sqr.isHighlight = false
        }
    }

    fun movePiece(from : String, to : String){

        val fromSquare = getSquareAt(from)
        val toSquare = getSquareAt(to)

        println("current side : $currentSide")
        println("from move piece $from(" + fromSquare.piece.toString() + " , " + fromSquare.clan.toString() + ") -> $to("+ toSquare.piece.toString() + " , " + toSquare.clan.toString() +") : ")

        if(fromSquare.clan == Clan.WHITE){
            currentSide = Clan.BLACK
        }
        else{
            currentSide = Clan.WHITE
        }


        if(fromSquare.clan == Clan.BLACK){
            currentSide = Clan.WHITE
            occupiedSquares.blackOccupiedSquares.remove(from)
            occupiedSquares.blackOccupiedSquares.add(to)
        }
        if(fromSquare.clan == Clan.WHITE){
            occupiedSquares.whiteOccupiedSquares.remove(from)
            occupiedSquares.whiteOccupiedSquares.add(to)
        }

        if(toSquare.clan == Clan.BLACK){
            occupiedSquares.blackOccupiedSquares.remove(to)
            capturedPieces.byWhiteCapturedPieces.add(toSquare.piece)
        }else if(toSquare.clan == Clan.WHITE){
            occupiedSquares.whiteOccupiedSquares.remove(to)
            capturedPieces.byBlackCapturedPieces.add(toSquare.piece)
        }



        //move mechanism
        setSquareAt(to, getSquareAt(from))
        setEmptySquareAt(from)

        println("from move piece $from(" + getSquareAt(from).piece.toString() + " , " + getSquareAt(from).clan.toString() + ") -> $to("+ getSquareAt(to).piece.toString() + " , " + getSquareAt(to).clan.toString() +") : ")
        println("current side : $currentSide")

        println("captured pieces white : " + capturedPieces.byWhiteCapturedPieces)
        println("captured pieces black : " + capturedPieces.byBlackCapturedPieces)
    }
}