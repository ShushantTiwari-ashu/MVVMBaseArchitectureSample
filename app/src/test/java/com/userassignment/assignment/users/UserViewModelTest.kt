package com.userassignment.assignment.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.userassignment.assignment.testUtil.MainCoroutineRule
import com.userassignment.assignment.testUtil.TestCommonObjects.application
import com.userassignment.assignment.testUtil.TestCommonObjects.booleanTypeObserver
import com.userassignment.assignment.testUtil.TestCommonObjects.meta
import com.userassignment.assignment.testUtil.TestCommonObjects.userResponse
import com.userassignment.assignment.testUtil.TestConstants.EMPTY_STRING
import com.assignment.userassignment.main.dataclass.UserDataClass
import com.assignment.userassignment.main.dataclass.UsersResponseClass
import com.assignment.userassignment.main.repository.UserRepository
import com.assignment.userassignment.main.viewmodel.UserViewModel
import com.assignment.userassignment.network.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val cRule = MainCoroutineRule()

    private val repository: UserRepository = mockk()
    private val userResponseObserver: Observer<Resource<UsersResponseClass>> = mockk(relaxed = true)
    private val rViewModel by lazy {
        UserViewModel(application, repository)
    }

    @Test
    fun testGetUser() {
        val mockResponse = flowOf(Resource(Resource.Status.SUCCESS, userResponse, EMPTY_STRING))
        coEvery { repository.fetchUsers() } answers { mockResponse }

        rViewModel.fetchUsers.observeForever(booleanTypeObserver)
        rViewModel.usersData.observeForever(userResponseObserver)
        rViewModel.fetchUsers()

        coVerify { booleanTypeObserver.onChanged(true) }
        coVerify { repository.fetchUsers() }
        coVerify {
            userResponseObserver.onChanged(
                Resource(
                    Resource.Status.SUCCESS,
                    userResponse,
                    EMPTY_STRING
                )
            )
        }

        with(rViewModel.usersData.value?.data) {
            assertEquals(meta, this?.metaDataClass)
            assertEquals(listOf<UserDataClass>(), this?.userDataList)
        }
        confirmVerified(repository, userResponseObserver)
    }

}