package com.david.androidapptemplate.repos

data class RepoResponse<D>(
    val data: D? = null,
    val status: ResultType = ResultType.SUCCESS,
    val throwable: Throwable = NullPointerException()
)

enum class ResultType {
    SUCCESS,
    FAIL,
}