package com.example.emptyactivity.Pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.emptyactivity.DataModels.Post
import com.example.emptyactivity.Layout.MainLayout
import com.example.emptyactivity.postListProvider
import java.time.LocalDate
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePost(modifier: Modifier = Modifier){

    var title by rememberSaveable { mutableStateOf("")}

    var content by rememberSaveable { mutableStateOf("")}

    var controversial by rememberSaveable {
        mutableFloatStateOf(0.5f)
    }

    var postList = postListProvider.current

    MainLayout {
        Column(modifier =
        modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Make someone laugh!",
                fontWeight = FontWeight.Bold,
                fontSize = 7.em,
                modifier = modifier.padding(bottom = 30.dp))

            Card{
                Column(modifier = modifier
                    .background(Color.White)
                    .height(200.dp)) {
                    TextField(value = title, onValueChange = {
                        if(it.length <= 40){
                            title = it
                        }
                    }, label = {Text("Title")},
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Gray,
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f))

                    TextField(value = content, onValueChange = {
                        if(it.length <= 248){
                            content = it
                        }
                    },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Gray,
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        label = {Text("Joke...")},
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(2f))

                    Text(text = "Controversial Scale",
                        modifier = modifier
                            .weight(0.5f)
                            .padding(start = 10.dp),
                        fontSize = 4.em)

                    Slider(
                        value = controversial, onValueChange = {
                            controversial = it
                        },
                        enabled = true,
                        modifier = modifier
                            .weight(0.5f),
                        colors = SliderDefaults.colors(
                            thumbColor = Color(255,197,78),
                            activeTrackColor = Color(255,197,78),
                            inactiveTrackColor = Color(250,230,189),
                        )
                    )

                    Row(modifier = modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {


                        Button(onClick = {
                            postList.add(
                                Post(
                                    "${postList.size}",
                                    LocalDateTime.now(),
                                    title,
                                    content,
                                    listOf(),
                                    (controversial * 100).toInt(),
                                    listOf()
                                )
                            )
                        },
                            modifier = modifier
                                .padding(end = 10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(255,197,78))) {
                            Text(text = "Post")
                        }
                    }
                }
            }
        }
    }
}