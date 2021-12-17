package com.example.resepmasakanindo_160417065.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.resepmasakanindo_160417065.data.db.ResepDatabase
import com.example.resepmasakanindo_160417065.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val userLD = MutableLiveData<User>()
    val isLoggedInLD = MutableLiveData<Boolean>()
    val errorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val job = Job()
    private val userDao = ResepDatabase.getInstance(application.applicationContext).userDao
    private val sharedPreferences =
        getApplication<Application>().getSharedPreferences("app", Context.MODE_PRIVATE)

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun isLoggedIn() {
        loadingLD.value = true
        errorLD.value = false

        launch {
            try {
                val userId = sharedPreferences.getLong("userId", 0)

                isLoggedInLD.value = userId != 0.toLong()
                Log.d("USERVM", isLoggedInLD.value.toString())

                if (isLoggedInLD.value == true) userLD.value = userDao.findUser(userId)

                loadingLD.value = false
            } catch (e: Exception) {
                loadingLD.value = false
                errorLD.value = true
                Log.e("CHECK_USER", e.localizedMessage)
            }
        }
    }

    fun login(username: String, password: String) {
        loadingLD.value = true
        errorLD.value = false

        launch {
            try {
                userLD.value = userDao.login(username, password)
                sharedPreferences!!.edit {
                    putLong("userId", userLD.value!!.userId)
                }
                loadingLD.value = false
            } catch (e: Exception) {
                loadingLD.value = false
                errorLD.value = true
            }
        }
    }

    fun register(username: String, password: String) {
        loadingLD.value = true
        errorLD.value = false

        launch {
            try {
                val user = User(0, username, password)
                val insertId = userDao.register(user)
                val newUser = User(insertId, username, password)
                sharedPreferences!!.edit {
                    putLong("userId", insertId)
                }

                userLD.value = newUser
                loadingLD.value = false
            } catch (e: Exception) {
                loadingLD.value = false
                errorLD.value = true
                Log.e("REGISTER", e.localizedMessage)
            }
        }
    }
}