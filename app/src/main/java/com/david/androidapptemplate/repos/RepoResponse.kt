package com.david.androidapptemplate.repos


//************
// **NOTE
// not in use in this Branch
//*************
data class RepoResponse<D>(
    val data: D? = null,
    val status: ResultType = ResultType.SUCCESS,
    val throwable: Throwable = NullPointerException()
)

enum class ResultType {
    SUCCESS,
    FAIL,
}