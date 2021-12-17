package com.example.resepmasakanindo_160417065.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.resepmasakanindo_160417065.data.db.ResepDatabase
import com.example.resepmasakanindo_160417065.model.Kategori
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class KategoriViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val kategoriLD = MutableLiveData<List<Kategori>>()
    val errorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val job = Job()
    private val kategoriDao = ResepDatabase.getInstance(application.applicationContext).kategoriDao

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun loadKategori() {
        loadingLD.value = true
        errorLD.value = false

        launch {
            try {
                kategoriLD.value = kategoriDao.selectAllKategori()
                loadingLD.value = false
            } catch (e: Exception) {
                loadingLD.value = false
                errorLD.value = true
            }
        }
    }
}