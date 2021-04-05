package com.example.hellojavamodernandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        // 전역변수로 사용하는 것을 더 권장
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .build();

        // UI 갱신
        db.todoDao().getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // 버튼 클릭시 DB Insert
        findViewById(R.id.add_btn).setOnClickListener(v -> {
            new InsertAsyncTask(db.todoDao())
                    .execute(new Todo(mTodoEditText.getText().toString()));
        });
    }

    // Async Insert Method
    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao mTodoDao;

        public InsertAsyncTask(TodoDao mTodoDao) {
            this.mTodoDao = mTodoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.insert(todos[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends  AsyncTask<Todo, Void, Void> {
        private TodoDao mTodoDao;

        public UpdateAsyncTask(TodoDao mTodoDao) {
            this.mTodoDao = mTodoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.update(todos[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao mTodoDao;

        public DeleteAsyncTask(TodoDao mTodoDao) {
            this.mTodoDao = mTodoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.delete(todos[0]);
            return null;
        }
    }
}