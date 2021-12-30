package com.shushant.cleancode.network

import kotlinx.coroutines.flow.*

/**
 * This core function will handle database caching after performing network request
 */

inline fun <REMOTE> networkBoundResource(

    crossinline fetchFromRemote: () -> Flow<ApiResponse<REMOTE>>,
    crossinline processRemoteResponse: (response: ApiSuccessResponse<REMOTE>) -> Unit = { Unit },

    crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _: String?, _: Int -> Unit }
) = flow<Resource<REMOTE>> {

  emit(Resource.loading(null))

  fetchFromRemote().collect { apiResponse ->
    when (apiResponse) {
      is ApiSuccessResponse -> {
        processRemoteResponse(apiResponse)
        apiResponse.body?.let {
          emit(Resource.success(it))
        }
      }

      is ApiErrorResponse -> {
        onFetchFailed(apiResponse.errorMessage, apiResponse.statusCode)
        emit(
            Resource.error(apiResponse.errorMessage)
        )
      }

      is ApiEmptyResponse -> {
          emit(
              Resource.successNoData()
          )
      }
    }
  }

}
