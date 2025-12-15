package com.ui.ebook.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ui.ebook.common.BookCategoryModel
import com.ui.ebook.common.BookModel
import com.ui.ebook.common.ResultState
import com.ui.ebook.domain.repo.AllBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(val repo: AllBookRepo) : ViewModel() {

    private val _state: MutableState<ItemState> = mutableStateOf(ItemState())
    val state: MutableState<ItemState> = _state

    fun BringAllBooks() {
        viewModelScope.launch {
            repo.getAllBooks().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isloading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }

    fun BringAllCategory() {
        viewModelScope.launch {
            repo.getAllCategory().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isloading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(category = it.data)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }

    fun BringAllBooksByCategory(category: String) {
        viewModelScope.launch {
            repo.getAllBooksByCategory(category).collect {
                when (it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isloading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }
}

data class ItemState(
    val isloading: Boolean = false,
    val items: List<BookModel> = emptyList(),
    val error: String = "",
    val category: List<BookCategoryModel> = emptyList()
)