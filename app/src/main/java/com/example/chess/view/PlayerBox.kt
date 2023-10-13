package com.example.chess.view

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.chess.model.Clan
import com.example.chess.model.activeTimerColor
import com.example.chess.model.notActiveTimerColor
import com.example.chess.ui.theme.autourOne
import java.util.concurrent.TimeUnit

@Composable
fun playerBox(
    playerName :String,
    currentSide : Clan,
    clan : Clan
){

    var timerBGColor = if(currentSide == clan) activeTimerColor else notActiveTimerColor

    var timeRemaining = "10:00"

    Row(
        modifier = Modifier
            //.height(65.dp)
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .background(Color(0xFF262626), shape = RoundedCornerShape(5.dp))
            //.border(1.dp, Color(0xFFffffff), shape = RectangleShape)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = playerName,//cm.currentSide.toString(),
            fontFamily = autourOne,
            color = Color(0xFFf5f5f5),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(15.dp)
        )
        Row(
            modifier = Modifier
                //.height(65.dp)
                .padding(end = 20.dp)
                .background(timerBGColor, shape = RoundedCornerShape(5.dp))
                //.border(1.dp, Color(0xFFffffff), shape = RectangleShape)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(
                text = timeRemaining,//cm.currentSide.toString(),
                fontFamily = autourOne,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFf5f5f5),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .width(50.dp)
            )
        }

    }
}