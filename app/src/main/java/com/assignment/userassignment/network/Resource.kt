package com.assignment.userassignment.network

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        SUCCESS_NO_DATA,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }

      fun <T> successNoData(data: T? = null): Resource<T> {
        return Resource(
          Status.SUCCESS_NO_DATA,
          data,
          null
        )
      }
    }
}
