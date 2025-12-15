package com.ui.ebook.data.repoimpl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ui.ebook.common.BookCategoryModel
import com.ui.ebook.common.BookModel
import com.ui.ebook.common.ResultState
import com.ui.ebook.domain.repo.AllBookRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase): AllBookRepo{

    override fun getAllBooks(): Flow<ResultState<List<BookModel>>> = callbackFlow{
        trySend(ResultState.Loading)
        val valueEvent = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

               val items = snapshot.children.mapNotNull {
                   it.getValue(BookModel:: class.java)
                }
                trySend(ResultState.Success(items))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.child("Books").removeEventListener(valueEvent)
        }
    }

    override fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>> = callbackFlow{
        trySend(ResultState.Loading)
        val valueEvent = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val items = snapshot.children.mapNotNull {
                    it.getValue(BookCategoryModel:: class.java)
                }
                trySend(ResultState.Success(items))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("BooksCategory").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.child("BooksCategory").removeEventListener(valueEvent)
        }
    }

    override fun getAllBooksByCategory(category: String): Flow<ResultState<List<BookModel>>> = callbackFlow{
        trySend(ResultState.Loading)
        val valueEvent = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val items = snapshot.children.mapNotNull {
                 it.getValue(BookModel::class.java)
                }.filter { it.category==category }

                trySend(ResultState.Success(items))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.child("Books").removeEventListener(valueEvent)

        }
    }

}
