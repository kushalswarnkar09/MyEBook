package com.ui.ebook.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun AllBookScreen(
    navHostController: NavHostController,
    modifier: Modifier= Modifier,
    viewModel: ViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
){

        LaunchedEffect(Unit) {
           if (viewModel.state.value.items.isEmpty()) {
               viewModel.BringAllBooks()
           }
        }

        val res = viewModel.state.value
        when {
            res.isloading -> {
                if (viewModel.state.value.items.isEmpty()){
                    Column(
                        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.Gray,
                            trackColor = Color.Black
                        )
                        Text("Loading..")
                    }
                }
            }

            res.error.isNotEmpty() -> {
                Text(text = res.error, modifier = modifier)
            }

            res.items.isNotEmpty() -> {
                Column(Modifier.fillMaxSize()){
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                    ) {

                        items(res.items) {
                            BookCart(
                                imageUrl = it.image,
                                title = it.bookName,
                                author = it.bookAuthor,
                                description = it.bookDescription,
                                navHostController = navHostController,
                                bookUrl = it.bookUrl
                            )


                        }
                    }
                }
            }

            else -> Text(text = "No Books Available", modifier = modifier)
        }
    }

