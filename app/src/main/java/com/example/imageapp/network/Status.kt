package com.example.imageapp.network

sealed class Status {
    object Success : Status()
    object Error : Status()
    object Loading : Status()
}