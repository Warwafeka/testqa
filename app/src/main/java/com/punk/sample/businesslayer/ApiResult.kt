package com.punk.sample.businesslayer

sealed class ApiResult<T>
data class Success<T>(val result: T): ApiResult<T>()
data class Fail<T>(val error: Throwable): ApiResult<T>()