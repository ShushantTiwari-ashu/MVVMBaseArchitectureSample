package com.assignment.userassignment.network.api

import com.assignment.userassignment.main.dataclass.UsersResponseClass
import com.assignment.userassignment.network.Resource
import com.assignment.userassignment.network.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getUsersData(): Flow<Resource<UsersResponseClass>> {
        return networkBoundResource(
            fetchFromRemote = {
                apiService.getUsersData()
            },
            processRemoteResponse = { },
            onFetchFailed = { _, _ -> }
        ).flowOn(ioDispatcher)
    }

}
