#  Welcome to Android Kotlin Boilerplate!

This repository provides a boilerplate codebase for developing Android applications using Kotlin. It aims to provide a starting point with preconfigured project structure, common libraries, and best practices to kickstart your Android development process.

## Table of Contents
* [Getting Started](#getting-started)
* [Softwares and Version Install Android](#software-and-version-install-android)
* [Features](#features)
* [Clone Project](#clone-project)
* [Folder Structure](#folder-structure)
* [Build variants](#build-variants)
* [Dependencies](#dependencies)
* [Test Dependencies](#test-dependencies)
* [LICENSE](#license)


## Getting Started

This project is a starting point for a Android application.

A few resources to get you started if this is your first Android project:

- [Android Developer Documentation](https://developer.android.com/docs)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)

For help getting started with Android development, refer to the [official Android documentation](https://developer.android.com/), which provides tutorials, samples, guidance on mobile development, and a comprehensive API reference.
-   An Android app with  MVVM  architectural pattern.
- _ViewBinding_  for activities and fragments.
-   Android Studio  _EditorConfig_  file to maintain consistent coding styles.

# Softwares and Version Install Android:

https://docs.flutter.dev/get-started/install  
Install Android Studio : Android Studio Dolphin | 2021.3.1 Patch 1

- Android supported version:
  - compileSdk 33
  - minSdk 21
  - targetSdk 33
  - versionCode 1
  - versionName "1.0"
  - jvmTarget '1.8'


## Features
* [Authentication](#authentication)
* [Dashboard](#dashboard)

# Clone Project :

git clone https://github.com/TechnourceOfficial/Android_Kotlin_Boilerplate
After cloning the project, you can open it in Android Studio by following these steps:
1.  Open Android Studio.
2.  Select "Open an existing Android Studio project" from the welcome screen.
3.  Navigate to the directory where you cloned the project and select the project folder.
4.  Click "OK" to open the project in Android Studio.
5.  Android Studio will take some time to index and set up the project.
6.  Once the indexing is complete, you can start working on the Android project in Android Studio.

# Folder Structure:
```commandline
.
├── app                    - Folder which contains example application
│   ├── src
│   └── main
│   ├── manifests       
│   └── java
│   	├── com.technource.android
│   		├── base
│   		├── commonInterface     
│   		├── preference
│   		├── ui
│   		├── utils    
│   ├── assets
│   ├── res
│   	├── anim
│   	├── color
│   	├── drawable
│   	├── font
│   	├── layout
│   	├── menu
│   	├── mipmap
│   	├── values
│   		├── colors
│   		├── dimens
│   		├── strings
|		├── xml
├── build.gradle
├── gradle                 - Folder for gradle build tool
├── gradle.properties      - File for gradle configuration
├── gradlew
├── gradlew.bat
├── project.properties
├── README.md
└── settings.gradle        - File for graddle setting
```
## Build variants

Herein you can find multiple targets that the app takes into account:
Where the following formed variants are built for staging purposes:

-   stagingInternalDebug
-   stagingInternalRelease

And these ones for production purposes:

-   productionInternalDebug
-   productionInternalRelease
-   productionExternalDebug
-   productionExternalRelease

##   Dependencies
- App Localization
  implementation 'dev.b3nedikt.applocale:applocale:3.0.0'  
  implementation 'dev.b3nedikt.reword:reword:4.0.2'
- PinView  
  implementation 'io.github.chaosleung:pinview:1.4.4'
- Glide  
  implementation 'com.github.bumptech.glide:glide:4.14.2'  
  kapt 'com.github.bumptech.glide:compiler:4.14.2'
- Image picker  
  implementation 'com.github.dhaval2404:imagepicker:2.1'

## Test Dependencies

-   [JUnit](https://github.com/junit-team/junit4) - a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
-   [AndroidX](https://github.com/android/android-test) - the androidx test library provides an extensive framework for testing Android apps.

## Authentication
- Registration Screen
- Login Screen
- Forgot-password Screen
- OTP Verification Screen
- Reset Password Screen
- Edit Profile Screen
  - Upload Photo from Camera & Gallery
   
## Application Language
- English
- French
- Russian

## Dashboard
-   Dashboard UI with Bottom TabNavigation
-  AboutUs Screen
-  Terms-Conditions Screen
- Privacy-Policy Screen
- Change Language Screen
- Logout Screen

## Application Preview

https://github.com/TechnourceOfficial/Android_Kotlin_Boilerplate/assets/70566076/5d14c036-2291-4c57-a902-ac20892f3194

## License

- This project is licensed under the [MIT License](LICENSE)
