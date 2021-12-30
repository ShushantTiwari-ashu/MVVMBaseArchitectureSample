package com.shushant.cleancode.testUtil

import androidx.lifecycle.Observer
import com.shushant.cleancode.testUtil.TestConstants.EMAIL
import com.shushant.cleancode.testUtil.TestConstants.GENDER
import com.shushant.cleancode.testUtil.TestConstants.ID
import com.shushant.cleancode.testUtil.TestConstants.LIMIT
import com.shushant.cleancode.testUtil.TestConstants.NAME
import com.shushant.cleancode.testUtil.TestConstants.PAGE
import com.shushant.cleancode.testUtil.TestConstants.PAGES
import com.shushant.cleancode.testUtil.TestConstants.STATUS
import com.shushant.cleancode.testUtil.TestConstants.TOTAL
import com.shushant.cleancode.testUtil.TestConstants.URL1
import com.shushant.cleancode.testUtil.TestConstants.URL2
import com.shushant.cleancode.main.dataclass.*
import com.shushant.cleancode.network.Resource
import com.shushant.cleancode.network.api.ApiHelper
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

object TestCommonObjects {
    val application = mockk<com.shushant.cleancode.application.CleanCodeApp>()
    val apiHelper: ApiHelper = mockk()
    val userDataClass = mockk<UserDataClass>(relaxed = true)
    val metaDataClass = mockk<MetaDataClass>(relaxed = true)
    val usersResponseClass = mockk<UsersResponseClass>(relaxed = true)
    val paginationDataClass = mockk<PaginationDataClass>(relaxed = true)
    var linksDataClass = mockk<LinksDataClass>(relaxed = true)


    //Api
    val stringResourceSuccessObject = Resource(
        Resource.Status.SUCCESS, String(),
        TestConstants.EMPTY_STRING
    )
    val stringResourceLoadingObject = Resource(
        Resource.Status.LOADING, String(),
        TestConstants.EMPTY_STRING
    )
    val stringResourceSuccessNoDataObject = Resource(
        Resource.Status.SUCCESS_NO_DATA, String(),
        TestConstants.EMPTY_STRING
    )
    val stringResourceErrorObject = Resource(
        Resource.Status.ERROR, String(),
        TestConstants.EMPTY_STRING
    )
    val stringMockResponse = flowOf(stringResourceSuccessObject)
    val stringResourceObserver: Observer<Resource<String>> = mockk(relaxed = true)

    //data types
    val booleanTypeObserver: Observer<Boolean> = mockk(relaxed = true)
    val stringTypeObserver: Observer<String> = mockk(relaxed = true)


    val links = LinksDataClass(null, URL1, URL2)
    val page = PaginationDataClass(TOTAL, PAGES, PAGE, LIMIT, links)
    val meta = MetaDataClass(page)
    val user = UserDataClass(ID, NAME, EMAIL, GENDER, STATUS)
    val userResponse = UsersResponseClass(meta, listOf())

}
