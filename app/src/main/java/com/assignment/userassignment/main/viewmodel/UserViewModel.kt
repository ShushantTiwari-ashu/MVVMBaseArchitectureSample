package com.assignment.userassignment.main.viewmodel

import androidx.lifecycle.*
import com.assignment.userassignment.common.BaseViewModel
import com.assignment.userassignment.main.dataclass.UserDataClass
import com.assignment.userassignment.main.dataclass.UsersResponseClass
import com.assignment.userassignment.main.repository.UserRepository
import com.assignment.userassignment.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
open class UserViewModel @Inject constructor(
    application: com.assignment.userassignment.application.AssignmentApplication,
    private val userRepository: UserRepository
) : BaseViewModel(application) {

    val fetchUsers by lazy { MutableLiveData<Boolean>() }
    val usersListInsertResponse by lazy { MutableLiveData<List<Long>>() }
    val usersLists by lazy { MutableLiveData<List<UserDataClass>>() }

    val usersData: LiveData<Resource<UsersResponseClass>> =
        Transformations.switchMap(fetchUsers) {
            userRepository.fetchUsers().map {
                resourceStatus(it)
                it

            }.asLiveData(viewModelScope.coroutineContext)
        }

    fun fetchUsers() {
        fetchUsers.postValue(true)
    }

    fun saveToDB(it: Resource<UsersResponseClass>?) {
        usersListInsertResponse.postValue(it?.data?.userDataList?.let { it1 ->
            databaseRepository.insertUsers(
                it1
            )
        })

    }

    fun getUsersFromDb() {
        usersLists.postValue(databaseRepository.getUsersList())
    }

}