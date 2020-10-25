package com.david.androidapptemplate.repos

data class RepoResponse<D>(
    val data: D?,
    val status: ResultType = ResultType.SUCCESS,
    val err: Exception = NullPointerException()
)

enum class ResultType {
    SUCCESS,
    FAIL,
}