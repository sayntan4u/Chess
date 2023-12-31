package com.example.chess.view

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.model.CapturedPieces
import com.example.chess.model.Clan
import com.example.chess.model.activeTimerColor
import com.example.chess.model.chessPieceUnicodeBlack
import com.example.chess.model.chessPieceUnicodeWhite
import com.example.chess.model.notActiveTimerColor

import com.example.chess.ui.theme.archivo
import com.example.chess.ui.theme.autourOne
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun playerBox(
    playerName :String,
    currentSide : Clan,
    clan : Clan,
    capturedPieces : CapturedPieces
){

    var secondsLeft by remember { mutableIntStateOf(599) }
    var isPaused by remember { mutableStateOf(false) }
    var timeLeft by remember {
        mutableStateOf("10:00")
    }
    var timerBGColor = if(currentSide == clan){
        if(secondsLeft < 60){
            Color(0xFFff4d4d)
        }
        else{
            activeTimerColor
        }
    } else{
        notActiveTimerColor
    }

    isPaused = currentSide != clan

    LaunchedEffect(key1 = secondsLeft, key2 = isPaused, key3 = timeLeft) {
        while (secondsLeft > 0 && !isPaused) {
            delay(1000L)

            var min = (secondsLeft / 60).toString()
            var sec = (secondsLeft % 60).toString()

            if(min.toString().count() < 2){
               min = "0$min"
            }
            if(sec.toString().count() < 2){
                sec = "0$sec"
            }

            timeLeft = "$min:$sec"

            secondsLeft--

        }
    }

    Row(
        modifier = Modifier
            //.height(65.dp)
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .background(Color(0xFF727272), shape = RoundedCornerShape(5.dp))
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Column {
            Text(
                text = playerName,//cm.currentSide.toString(),
                fontFamily = autourOne,
                fontSize = 14.sp,
                color = Color(0xFF262626),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp, bottom = 5.dp)
            )
            Row(
                modifier = Modifier

                    .padding(start = 15.dp, top = 5.dp, bottom = 5.dp)
                    .height(20.dp),
                verticalAlignment = Alignment.CenterVertically) {

                if(clan == Clan.WHITE){
                    for(p in capturedPieces.byWhiteCapturedPieces){
                        Text(
                            text= chessPieceUnicodeBlack[p].toString(),
                            //fontSize = 14.sp,
                            modifier = Modifier
                        )
                    }
                }
                else{
                    for(p in capturedPieces.byBlackCapturedPieces){
                        Text(
                            text= chessPieceUnicodeWhite[p].toString(),
                            modifier = Modifier
                        )
                    }
                }

            }

        }


        /*
        Timer Implementation
         */

        Row(
            modifier = Modifier
                //.height(65.dp)
                .padding(end = 15.dp)
                .background(timerBGColor, shape = RoundedCornerShape(5.dp))
                //.border(1.dp, Color(0xFFf5f5f5), shape = RoundedCornerShape(5.dp))
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically)
        {

            Text(
                text = timeLeft,
                fontFamily = archivo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFf5f5f5),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .width(60.dp)
                    //.background(Color.Cyan)
            )
        }

    }
}