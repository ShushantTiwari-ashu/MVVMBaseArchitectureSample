package com.shushant.cleancode.main.repository

import com.shushant.cleancode.main.dataclass.UsersResponseClass
import com.shushant.cleancode.network.Resource
import com.shushant.cleancode.network.api.ApiHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    fun fetchUsers(): Flow<Resource<UsersResponseClass>> = apiHelper.getUsersData()
}