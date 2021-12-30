package com.shushant.cleancode.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shushant.cleancode.testUtil.TestCommonObjects.apiHelper
import com.shushant.cleancode.testUtil.TestCommonObjects.userResponse
import com.shushant.cleancode.testUtil.TestConstants
import com.shushant.cleancode.main.repository.UserRepository
import com.shushant.cleancode.network.Resource
import io.mockk.every
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersRepositoryTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val rRepository by lazy {
        UserRepository(apiHelper)
    }

    @Test
    fun testGetUser() {
        val expectedResponse =
            flowOf(Resource(Resource.Status.SUCCESS, userResponse, TestConstants.EMPTY_STRING))
        every { apiHelper.getUsersData() } answers { expectedResponse }
        val actualResponse = rRepository.fetchUsers()
        verify(exactly = 1) { apiHelper.getUsersData() }
        assertEquals(expectedResponse, actualResponse)
    }

}