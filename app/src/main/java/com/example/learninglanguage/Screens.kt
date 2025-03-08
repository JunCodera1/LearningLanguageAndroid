package com.example.learninglanguage

sealed class Screens (val screen: String){
    data object Home: Screens("home")
    data object Search: Screens("search")
    data object Notification: Screens("notification")
    data object Profile: Screens("profile")
    data object Settings: Screens("settings")
    data object Login: Screens("login")
    data object SignUp: Screens("signup")



}