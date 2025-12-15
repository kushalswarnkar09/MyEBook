package com.ui.ebook.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes(){

    @Serializable
    object HomeScreen

    @Serializable
    data class BookByCategory(val category: String)

    @Serializable
    data class ShowPDFScreen(val bookUrl: String)





}