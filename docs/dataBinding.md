# Data Binding

데이터 바이딩을 사용하면 XML에 데이터를 넘겨줄 수 있다



`build.gradle`파일에 아래의 코드를 추가

```bash
android {
        ...
        dataBinding {
            enabled = true
        }
    }
```



```xml
android:text="@{viewModel.newTodo}" // 단방향 통신
android:text="@={viewModel.newTodo}" // 양방향 통신
```



## Question

```kotlin
package com.example.hellomodernandroid

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}
```

`suspend`사용하였을 때 **Error 발생**



## 참고 자료

https://developer.android.com/topic/libraries/data-binding

https://developer.android.com/topic/libraries/data-binding/start