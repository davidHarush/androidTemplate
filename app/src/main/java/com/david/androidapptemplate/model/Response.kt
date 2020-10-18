package com.david.androidapptemplate.model

data class Response<D>(
    val data: D?,
    val status: ResultType = ResultType.SUCCESS,
    val err: Exception = NullPointerException()
)

enum class ResultType {
    SUCCESS,
    FAIL,
}