package com.route.islamie_app101.ui.utils

sealed class Resource<T> {
    class Initial<T>(): Resource<T>()
    class Loading<T>(): Resource<T>()
    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val errorMessage: String): Resource<T>()
}