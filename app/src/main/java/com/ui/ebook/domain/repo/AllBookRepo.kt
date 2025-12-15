package com.ui.ebook.domain.repo

import com.ui.ebook.common.BookCategoryModel
import com.ui.ebook.common.BookModel
import com.ui.ebook.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {
    fun getAllBooks(): Flow<ResultState<List<BookModel>>>
    fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>>
    fun getAllBooksByCategory(category: String): Flow<ResultState<List<BookModel>>>
}