package com.ui.ebook.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookByCategoryScreen(category: String, navHostController: NavHostController,   viewModel: ViewModel = hiltViewModel(LocalContext.current as ComponentActivity)) {
    LaunchedEffect(Unit) {
        if (viewModel.state.value.items.isEmpty()) {
            viewModel.BringAllBooksByCategory(category)
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(category) },
                navigationIcon = {
                    IconButton(onClick = {navHostController.popBackStack()}) {
                        Icon(Icons.Default.SubdirectoryArrowLeft,null)
                    }
                }
            )
        }
    ) {innerpadding->
        val res = viewModel.state.value

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerpadding)) {
            when{
                res.isloading->{
                    if (viewModel.state.value.items.isEmpty()){
                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
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
                    Text(res.error)
                }
                res.items.isNotEmpty()->{
                    Column(Modifier.fillMaxSize()) {
                        LazyColumn(Modifier.fillMaxSize()) {
                            items(res.items){
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
            }
        }
    }
}