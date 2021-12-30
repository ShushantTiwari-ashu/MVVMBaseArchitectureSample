package com.shushant.cleancode.main.viewmodel

import com.shushant.cleancode.common.BaseViewModel
import com.shushant.cleancode.main.dataclass.UserDataClass
import com.shushant.cleancode.main.dataclass.UsersResponseClass
import com.shushant.cleancode.main.repository.UserRepository
import com.shushant.cleancode.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
open class UserViewModel @Inject constructor(
    application: com.shushant.cleancode.application.CleanCodeApp,
    private val userRepository: UserRepository
) : BaseViewModel(application) {

    val fetchUsers by lazy { MutableStateFlow(false) }
    val usersListInsertResponse by lazy { MutableStateFlow<List<Long>>(emptyList()) }
    val usersLists by lazy { MutableStateFlow<List<UserDataClass>>(emptyList()) }

    @ExperimentalCoroutinesApi
    val usersData = fetchUsers.flatMapLatest {
        userRepository.fetchUsers().map {
            resourceStatus(it)
            it
        }
    }

    fun fetchUsers() {
        fetchUsers.value = true
    }

    fun saveToDB(it: Resource<UsersResponseClass>?) {
        it?.data?.userDataList?.let { it1 ->
            databaseRepository.insertUsers(
                it1
            )
        }?.let { it2 -> usersListInsertResponse.tryEmit(it2) }

    }

    fun getUsersFromDb() {
        usersLists.tryEmit(databaseRepository.getUsersList())
    }

}