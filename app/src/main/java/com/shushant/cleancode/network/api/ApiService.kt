package com.shushant.cleancode.network.api

import com.shushant.cleancode.main.dataclass.UsersResponseClass
import com.shushant.cleancode.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    fun getUsersData(): Flow<ApiResponse<UsersResponseClass>>

}
