package com.example.chess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.chess.model.ChessModel
import com.example.chess.model.Clan
import com.example.chess.model.Piece
import com.example.chess.ui.theme.ChessTheme
import com.example.chess.ui.theme.autourOne
import com.example.chess.view.board

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var cm = ChessModel()
        setContent {
            ChessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF4c4c4c)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                //.height(65.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF262626), shape = RoundedCornerShape(5.dp))
                                //.border(1.dp, Color(0xFFffffff), shape = RectangleShape)
                                .height(60.dp),
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                text = cm.currentSide.toString(),
                                fontFamily = autourOne,
                                color = Color(0xFFf5f5f5),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }


                        board(cm)

                        Row(
                            modifier = Modifier
                                //.height(65.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF262626), shape = RoundedCornerShape(5.dp))
                                //.border(1.dp, Color(0xFFffffff), shape = RectangleShape)
                                .height(60.dp),
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                text = "Player 2",
                                fontFamily = autourOne,
                                color = Color(0xFFf5f5f5),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }


                    }
                }
            }
        }
    }

}


