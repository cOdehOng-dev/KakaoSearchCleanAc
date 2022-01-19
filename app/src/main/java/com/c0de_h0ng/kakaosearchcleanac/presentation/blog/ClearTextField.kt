package com.c0de_h0ng.kakaosearchcleanac.presentation.blog

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.c0de_h0ng.kakaosearchcleanac.R

/**
 * Created by c0de_h0ng on 2022/01/19.
 */
@Preview
@Composable
fun ClearTextField(
) {
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
        placeholder = { Text(
            text = "검색어를 입력해주세요.",
            color = Color.Black,
            fontFamily = FontFamily(
                Font(R.font.kakao_regular)
            ),
            textAlign = TextAlign.Start
        )},

        textStyle = textStyle

        //label = { Text("Label") }

    )
}