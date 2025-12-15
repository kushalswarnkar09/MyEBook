package com.ui.ebook.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ui.ebook.presentation.AllBookScreen
import com.ui.ebook.presentation.BookByCategoryScreen
import com.ui.ebook.presentation.CategoryScreen
import com.ui.ebook.presentation.HomeScreen
import com.ui.ebook.presentation.PDFViewerScreen

@Composable
fun NavGraph(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Routes.HomeScreen){

        composable<Routes.HomeScreen>{
            HomeScreen(navHostController = navHostController)
        }

        composable<Routes.ShowPDFScreen> {backStackEntry->
            val data: Routes.ShowPDFScreen = backStackEntry.toRoute()
            PDFViewerScreen(bookUrl = data.bookUrl)
        }

        composable<Routes.BookByCategory> {backStackEntry->
            val data1: Routes.BookByCategory = backStackEntry.toRoute()
            BookByCategoryScreen(category = data1.category, navHostController = navHostController)
        }



    }
}