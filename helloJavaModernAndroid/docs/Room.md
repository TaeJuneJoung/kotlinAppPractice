# Room

해당 내용은 [오준석님의 **오준석의 생존코딩** 유튜브 강의](https://www.youtube.com/channel/UCz9n4yRsYYryRjrSCK0-YWA) 학습 내용을 기재하였습니다.



Database SQLite가 아닌 Room Library를 사용하라고 안드로이드가 공식적으로 권고



Room Library를 편하게 추가하기 위해 MainActivity에서 Room 라이브러리를 의존성 주입

그 후에, 해당 부분을 지우고 **build.gradle(:app)**에 가보면 아래와 같이 추가된 것을 확인할 수 있다

```bash
dependencies {
	...
	implementation 'androidx.room:room-runtime:2.2.5'
	annotationProcessor 'androidx.room:room-compiler:2.2.5'
}
```



> :tipping_hand_man:**tip. Getter & Setter 등의 단축 사용법**
>
> 클래스 영역 안에서 `Alt` + `Insert` 키 사용



실제 Database는 background에서도 동작되게 하여야하나, 입문상 확인하기만 하기 위해서 `.allowMainThreadQueries()`를 사용

```java
// MainActivity.onCreate()

AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries()
                .build();
```

이렇게 사용하면 백그라운드에서 동작은 안하지만 앱이 켜있는 동안은 확인 가능