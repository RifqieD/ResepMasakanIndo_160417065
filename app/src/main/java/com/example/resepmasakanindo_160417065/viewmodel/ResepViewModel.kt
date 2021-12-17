package com.example.resepmasakanindo_160417065.viewmodel

import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.resepmasakanindo_160417065.data.db.ResepDatabase
import com.example.resepmasakanindo_160417065.model.Resep
import com.example.resepmasakanindo_160417065.utils.ImageStorageManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ResepViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val resepLD = MutableLiveData<List<Resep>>()
    val errorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val job = Job()
    private val resepDao = ResepDatabase.getInstance(application.applicationContext).resepDao

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    fun loadResep() {
        loadingLD.value = true
        errorLD.value = false

        launch {
            try {
                resepLD.value = resepDao.selectAllResep()
                loadingLD.value = false
            } catch (e: Exception) {
                loadingLD.value = false
                errorLD.value = true
            }
        }
    }

    fun addResep(resep: Resep) {
        loadingLD.value = true
        errorLD.value = false

        launch {
            try {
                resepDao.insertResep(resep)
                loadingLD.value = false
            } catch (e: Exception) {
                loadingLD.value = false
                errorLD.value = true
            }
        }
    }
}