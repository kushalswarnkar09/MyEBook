package com.ui.ebook.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun CategoryScreen(
    navHostController: NavHostController,
    viewModel: ViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    LaunchedEffect(Unit) {
       if (viewModel.state.value.category.isEmpty()) {
           viewModel.BringAllCategory()
       }
    }
    Column(
        Modifier.fillMaxSize()
    ) {
        val res = viewModel.state.value

        when{
            res.isloading->{
                if (viewModel.state.value.category.isEmpty()){
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
            res.error.isNotEmpty()->{
                Text(text = res.error)
            }
            res.category.isNotEmpty()->{
                LazyVerticalGrid(
                    GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
                ){
                   items(res.category) {
                        BookCategoryCart(
                            image = it.categoryImgUrl,
                            category = it.name,
                            navHostController = navHostController
                        )
                    }
                }

            }
        }
    }
}