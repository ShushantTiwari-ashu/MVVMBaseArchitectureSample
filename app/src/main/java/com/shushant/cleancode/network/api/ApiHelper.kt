package com.shushant.cleancode.network.api

import com.shushant.cleancode.main.dataclass.UsersResponseClass
import com.shushant.cleancode.network.Resource
import com.shushant.cleancode.network.networkBoundResource
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
            processRemoteResponse = {

            },
            onFetchFailed = { _, _ -> }
        ).flowOn(ioDispatcher)
    }

}
