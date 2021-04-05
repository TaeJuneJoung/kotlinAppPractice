package com.example.hellomodernandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class MainViewModel(application: Application): AndroidViewModel(application) {
    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

   suspend fun insert(todo: Todo) { //suspend가 붙으면 해당 함수 호출은 코루틴 안에서 실행되어야함
        db.todoDao().insert(todo)
    }
}