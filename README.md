# WeatherApp
Application which forecast the Weather report for next 5 days

APPLICATION FOR WEATHER FORECAST FOR NEXT 5 DAYS
================================================

**TITLE** : **WEATHER APP**
**Description** - Simple Weather Application , which helps to Forecast the Weather Report for Next 5 days. It was created with Android / Kotlin

**Table of Contents **:
1. Developed Using
2. Way to Build the Project 
3. Run the App Using Virtual device 
4. Run the App Using the Physical device
5. Way to Test the Application 
6. What happen if we install First time on device

Developed Using:
===============
1. In this weather forcast Application, which was developed using 
        Developed for -> Android (native App)
        Programming Language -> Kotlin
        dependency Injection -> Dagger
        Architecture patter -> MVVM
        API Calls -> Retrofit
        Layouts -> Constrain layout

Way to Build the Project 
======================
1. Clone or Download Project the Github respository link of :- https://github.com/veeraaswinkumar/WeatherApp.git
2. Open the Project in to latest Android Studio IDE 
3. Install the required SDK and gradle files, if needed for the project
4. Once project Sources file gradle was Completed on Android Studio IDE , now can able to run the application.

Run the App Using Virtual device 
================================
1. Create the Android Virtual device using Android Virtual device manager present in the Android Studio
2. Choose the Mobile Model as you want to run the Application
3. Install the api Level up below 32(Android 12) , Bcz as per our project Target SDk was 32 (Android 12). So We can run the application
till Android 12
4. Once Virtual device was created, it was listed on android Virtual device manager 
5. Now click the Play icon present in the virtual device , then virtual device was started
6. Now click on the Play icon (present in the Toolbar in android Studio). application started to build on your Virtual device.

refer : https://developer.android.com/studio/run/managing-avds#:~:text=Create%20AVD%20Android%201%20Open%20the%20AVD%20Manager,settings%2C%20such%20as%20the%20skin.%20.%20See%20More.

Run the App Using the Physical device 
=====================================
1. Refer this Url -> https://developer.android.com/studio/debug/dev-options to enable the developer option on your mobile
2. Once developer option was enabled , connect the device with your system via USB Cable , Now Your device model Appear on the Toolbar
3. Click run icon (play icon) present near your device name in the android studio tool bar to build the application
4. Now the application was started build on your device.once build was generated. It will automatically installed on Your device
                                        or
 1. Android studio -> Build -> Build(Bundle's / Apk) -> Build Apk 
 2. Apk was start generating and it was generated in the source directory 
        app -> build -> outputs -> apk -> debug -> app-debug.apk
 3. Use that app-debug.apk file and transfer that file to device and run

Way to Test the Application 
===========================
1. Once Build was Installed on the device
2. First Screen could be the Splash screen 
3. Second screen which shows the weather forecast of Your Current Location.
4. IT shows Current Location name , temperature , weather report , along with Humidity and Low temperature, on the Top of the screen
5. In the below list it will list the next 5 day Forecast for every 3 hours for the day.
6. On the Top, there is option to Enter the city name and click submit Button , we can able to check the weather forecast for the particular city we looking far
 
What happen if we install First time on device : 
==============================================
1. Once you install the Application -> it ask some Runtime permission to access location / gps enables in the device
2. Once we allow permission , it enables the location . Once location enables it will take your latitude and Longitude values to show the weather forecast
3. If we deny the runtime permission -> It wont show any Weather Forecast on your device

**Note : This Application which requires Interner connection and location enable is mandatory. if we deny that we not able to forecast the Weather
it will show the empty screen along the alert msg No Internet connection / Not able to access your location respectively.
**
