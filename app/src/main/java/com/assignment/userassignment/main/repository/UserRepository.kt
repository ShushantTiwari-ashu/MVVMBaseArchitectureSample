package com.assignment.userassignment.main.repository

import com.assignment.userassignment.main.dataclass.UsersResponseClass
import com.assignment.userassignment.network.Resource
import com.assignment.userassignment.network.api.ApiHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    fun fetchUsers(): Flow<Resource<UsersResponseClass>> = apiHelper.getUsersData()
}