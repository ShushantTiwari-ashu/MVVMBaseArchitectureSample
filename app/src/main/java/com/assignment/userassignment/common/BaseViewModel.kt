package com.assignment.userassignment.common

import androidx.lifecycle.AndroidViewModel
import com.assignment.userassignment.database.DatabaseRepository
import com.assignment.userassignment.network.Resource
import javax.inject.Inject

abstract class BaseViewModel(assignmentApplication: com.assignment.userassignment.application.AssignmentApplication) :
    AndroidViewModel(assignmentApplication) {

    @Inject
    protected lateinit var databaseRepository: DatabaseRepository

    protected fun <T> resourceStatus(status: Resource<T>) {
        when (status.status) {
            Resource.Status.LOADING -> {
                Resource.loading(null)
            }
            Resource.Status.SUCCESS -> {
                Resource.success(status.data)
            }
            Resource.Status.SUCCESS_NO_DATA -> {
                Resource.successNoData(status.data)
            }
            Resource.Status.ERROR -> {
                Resource.error(status.message!!, null)
            }
        }
    }

}