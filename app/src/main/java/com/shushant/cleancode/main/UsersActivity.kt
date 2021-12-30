package com.shushant.cleancode.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shushant.cleancode.databinding.ActivityUsersBinding
import com.shushant.cleancode.main.adapter.UserListAdapter
import com.shushant.cleancode.main.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityUsersBinding
    private val mViewModel: UserViewModel by viewModels()
    private val userAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViews()
        setUpObservers()
        fetchUsers()
    }

    private fun initViews() {
        mBinding.rvUsers.adapter = userAdapter
    }

    private fun fetchUsers() {
        mViewModel.fetchUsers()
    }

    private fun setUpObservers() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                launch {
                    mViewModel.usersData.collect {
                        mViewModel.saveToDB(it)
                    }
                }
                launch {
                    mViewModel.usersListInsertResponse.collect{
                        mViewModel.getUsersFromDb()
                    }
                }
                launch {
                    mViewModel.usersLists.collect {
                        userAdapter.updateList(it)
                    }
                }
            }
            cancel()
        }




    }

}