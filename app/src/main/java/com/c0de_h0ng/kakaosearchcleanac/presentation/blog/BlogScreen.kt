package com.c0de_h0ng.kakaosearchcleanac.presentation.blog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.c0de_h0ng.kakaosearchcleanac.R

/**
 * Created by c0de_h0ng on 2022/01/18.
 */
@Preview
@Composable
fun BlogScreen(

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 15.dp, 0.dp, 15.dp)
            .background(Color.White)
            .height(58.dp)
        ) {
            Image(
                painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                    .align(CenterVertically)
            )

            var text by remember { mutableStateOf("") }
            val textStyle = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(
                    Font(R.font.kakao_regular)
                ),
                textAlign = TextAlign.Start
            )

            TextField(
                value = text,
                onValueChange = { text = it },
                maxLines = 2,
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(backgroundColor = Color.White)
                    ,

                textStyle = textStyle,
                placeholder = { Text(
                    text = "검색어를 입력해주세요.",
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.kakao_regular)
                    ),
                    textAlign = TextAlign.Start
                )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)

                //label = { Text("Label") }

            )
            //ClearTextField()
        }
    }

}