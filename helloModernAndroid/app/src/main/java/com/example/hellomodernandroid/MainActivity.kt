package com.example.hellomodernandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        db.todoDao().getAll().observe(this, Observer { todos ->
            result_text.text = todos.toString()
        })

        add_btn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) { // Background Thread
                db.todoDao().insert(Todo(todo_edit.text.toString()))
            }
        }
    }
}