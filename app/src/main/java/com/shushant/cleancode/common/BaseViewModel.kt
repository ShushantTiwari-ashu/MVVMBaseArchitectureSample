package com.shushant.cleancode.common

import androidx.lifecycle.AndroidViewModel
import com.shushant.cleancode.database.DatabaseRepository
import com.shushant.cleancode.network.Resource
import javax.inject.Inject

abstract class BaseViewModel(cleanCodeApp: com.shushant.cleancode.application.CleanCodeApp) :
    AndroidViewModel(cleanCodeApp) {

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