package com.example.hellomodernandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAll().observe(this, Observer { todos ->
            result_text.text = todos.toString()
        })

        add_btn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) { // Background Thread
                viewModel.insert(Todo(todo_edit.text.toString()))
            }
        }
    }
}