package com.c0de_h0ng.kakaosearchcleanac.presentation.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.c0de_h0ng.kakaosearchcleanac.R

/**
 * Created by c0de_h0ng on 2022/01/19.
 */
@ExperimentalComposeUiApi
@Preview
@Composable
fun ClearTextField(
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(20.dp, 15.dp, 0.dp, 15.dp)
                .background(Color.White)
                .height(58.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                null,
                tint = Color.Yellow,
                modifier = Modifier
                    .padding(20.dp, 15.dp, 12.dp, 15.dp)
                    .width(32.dp)
                    .height(32.dp)
                    .align(Alignment.CenterVertically)
            )
//            Image(
//                painterResource(id = R.drawable.ic_search),
//                contentDescription = "",
//                contentScale = ContentScale.Fit,
//                modifier = Modifier
//                    .padding(20.dp, 15.dp, 12.dp, 15.dp)
//                    .width(32.dp)
//                    .height(32.dp)
//                    .align(Alignment.CenterVertically)
//            )

            var text by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current

            val textStyle = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(
                    Font(R.font.kakao_regular)
                ),
                textAlign = TextAlign.Start
            )

            //검색창
//            BasicTextField(
//                value = text,
//                onValueChange = {text = it },
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Transparent),
//                maxLines = 1,
//
//            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    textStyle = textStyle,
                    maxLines = 1,
                    colors = TextFieldDefaults
                        .textFieldColors(
                            backgroundColor = Color.White,
                            textColor = Color.Black,
                            //UnderLine을 숨긴다.배경을 숨김
                            disabledTextColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),

                    placeholder = {
                        Text(
                            text = "검색어를 입력해주세요.",
                            modifier = Modifier.fillMaxSize(),
                            color = Color.Black,
                            fontFamily = FontFamily(
                                Font(R.font.kakao_regular)
                            ),
                            textAlign = TextAlign.Start
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
            }


        }
    }


}