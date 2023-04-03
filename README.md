# android-grillshow-app
This is a fan project for the YouTube channel "Die Grillshow"

## Tech stack and Libraries used
* Minimum SDK level: 26
* Kotlin, 100% Jetpack Compose, [Coroutines](https://developer.android.com/kotlin/coroutines) and Flow for asynchronous programming
* Jetpack libraries
    * [Jetpack Compose](https://developer.android.com/jetpack/compose): Android’s modern toolkit for building native UI
    * [Navigation Compose](https://developer.android.com/jetpack/compose/navigation): Navigate between composables while leveraging of the Navigation component’s infrastructure and features
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Encapsulates related business logic and manage UI data in a lifecycle-aware fashion
    * [Room](https://developer.android.com/training/data-storage/room): Persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite
    * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview): Library helps load and display pages of data from a larger dataset from local storage or over network.
    * [Koin]([https://developer.android.com/training/dependency-injection/hilt-android](https://insert-koin.io/)): Koin is a pragmatic lightweight dependency injection framework for Kotlin developers
    * [Retrofit2](https://github.com/square/retrofit): Type-safe REST client for Android and Java
    * [Gson](https://github.com/google/gson): Java library that can be used to convert Java Objects into their JSON representation and vice versa
* Architecture
    * MVVM Architecture
    * Repository pattern
* JUnit and Mockito for testing


## Architecture
This app follows [Google's official architecture guidance](https://developer.android.com/topic/architecture). It is based on the MVVM architecture and the Repository pattern.


## LICENSE
```
Apache License

Copyright 2023 Sebastian Hecken

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
