package com.example.hellomodernandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    var todos: LiveData<List<Todo>>

    var newTodo: String? = null

    init {
        todos = getAll()
    }

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

   fun insert(todo: String) { //suspend가 붙으면 해당 함수 호출은 코루틴 안에서 실행되어야함
       viewModelScope.launch(Dispatchers.IO) {
           db.todoDao().insert(Todo(todo))
       }
    }
}