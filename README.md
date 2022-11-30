# Compose Timer
*Assesment Task for Ather Energy*

## Description

* UI
    * [Compose](https://developer.android.com/jetpack/compose) declarative UI framework
    * [Material Design 3](https://m3.material.io/)

* Tech/Tools
    * [Kotlin](https://kotlinlang.org/) 100% coverage
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
      and [Flow](https://developer.android.com/kotlin/flow) for async operations
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for
      dependency injection
    * [Jetpack](https://developer.android.com/jetpack)
        * [Compose](https://developer.android.com/jetpack/compose)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that
          stores, exposes and manages UI state

* Modern Architecture
    * Single activity architecture
    * MVVM
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
      , [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions

## Architecture

The project use MVVM Architecture but adapted to Jetpack Compose.

Architecture layers:

* View - Composable screens that consume state, apply effects and delegate events.
* ViewModel - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that
  manages and reduces the state of the corresponding screen. Additionally, it intercepts UI events
  and produces side-effects. The ViewModel lifecycle scope is tied to the corresponding screen
  composable.
* Model - Repository classes that retrieve data. In a clean architecture context, one should use
  use-cases that tap into repositories. (Not used currently)

### Dependency injection

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is used for
Dependency Injection as a wrapper on top of [Dagger](https://github.com/google/dagger).
