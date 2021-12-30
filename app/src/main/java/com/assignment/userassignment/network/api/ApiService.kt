package com.assignment.userassignment.network.api

import com.assignment.userassignment.main.dataclass.UsersResponseClass
import com.assignment.userassignment.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    fun getUsersData(): Flow<ApiResponse<UsersResponseClass>>

}
