# README #

This README would normally document whatever steps are necessary to get your application up and running.

## What is this repository for? ##

* Practice on Android compose paradigm, using clean architecture and PokeApiV2
* v1.0.0

## Dependencies list and usage ##

* *androidx.activity:activity-compose*: UI building with Android Compose new declarative paradigm
* *com.google.dagger:hilt-android*: dependency injection library
* *androidx.hilt:hilt-navigation-compose*: navigation component integrated with hild and compose
* *androidx.room:room-* : store data onboard with key-value structure
* *com.squareup.retrofit2:retrofit*: perform REST calls
* *com.squareup.retrofit2:converter-gson*: library to perform object mapping
* *org.jetbrains.kotlinx:kotlinx-coroutines-* : handle long tasks in background threads
* *io.coil-kt:coil-compose*: asnycronous loading of images from web urls
* *androidx.test.ext:junit*: unit test TODO
* *androidx.test.espresso:espresso-core*: TODO

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


## App screenshots and high level behaviour ##

At launch the app downloads 20 pokemon details and stores the list into a local database for future access. The next time the user opens the app then, the list is retrieved from the db and the api calls are not performed, unless the user deletes ("erase" db button on toolbar) db entries to refresh the list ("refresh" button on toolbar).
The user can tap on a pokemon and see it's raw detail.

<p float="left">
  <img width="271" alt="Screenshot 2022-06-14 at 15 31 15" src="https://user-images.githubusercontent.com/107438397/173589489-62de0f96-ea43-4253-8843-acd30d85d69e.png">
  
  <img width="273" alt="Screenshot 2022-06-14 at 15 31 40" src="https://user-images.githubusercontent.com/107438397/173618575-abf3fea3-a4a7-4854-8042-795100741694.png">
</p>





