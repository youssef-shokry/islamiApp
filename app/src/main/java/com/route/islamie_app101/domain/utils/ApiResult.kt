package com.route.islamie_app101.domain.utils

sealed class ApiResult<T> {
    class Success<T>(val data: T): ApiResult<T>()
    class Error<T>(val errorMessage: String): ApiResult<T>()
}