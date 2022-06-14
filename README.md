# README #

This README would normally document whatever steps are necessary to get your application up and running.

## What is this repository for? ##

* Practice on Android compose paradigm, using clean architecture and PokeApiV2
* v1.0.0

## Dependencies list and usage ##

* androidx.activity:activity-compose: UI building with Android Compose new declarative paradigm
* com.google.dagger:hilt-android: dependency injection library
* androidx.hilt:hilt-navigation-compose: navigation component integrated with hild and compose
* androidx.room:room-* : store data onboard with key-value structure
* com.squareup.retrofit2:retrofit: perform REST calls
* com.squareup.retrofit2:converter-gson: library to perform object mapping
* org.jetbrains.kotlinx:kotlinx-coroutines-* : handle long tasks in background threads
* io.coil-kt:coil-compose: asnycronous loading of images from web urls
* androidx.test.ext:junit: unit test TODO
* androidx.test.espresso:espresso-core: TODO

## Project structure ##

```
app   
│
└─── data
│    │
│    └─── datasource
│    │
│    └─── di
│    │
│    └─── model
│    │
│    └─── network
│    │
│    └─── room
│         
└─── domain.usecases
│   
└─── presentation
│    │
│    └─── di
│    │
│    └─── navigation
│    │
│    └─── theme
│    │
│    └─── ui.screens
│         │
│         └───components
│         │
│         └─── pokemonDetail
│         │
│         └─── pokemonList
│         │
│         └─── splash
```


## App screenshots ##

<div class="row">
  <div class="column">
    <img width="271" alt="Screenshot 2022-06-14 at 15 31 15" src="https://user-images.githubusercontent.com/107438397/173589489-62de0f96-ea43-4253-8843-acd30d85d69e.png">
  </div>
  <div class="column">
    <img width="273" alt="Screenshot 2022-06-14 at 15 31 40" src="https://user-images.githubusercontent.com/107438397/173589502-fd59be54-42cf-40c0-91fa-    e369462c1fd2.png">
  </div>
</div>





