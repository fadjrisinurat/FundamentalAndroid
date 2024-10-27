package com.example.fundamental.data.database

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamental.ui.DetailViewModel
import com.example.fundamental.ui.FavoriteViewModel

class FavoriteViewModelFactory (private val repository: FavoriteRepository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    companion object {
        @Volatile
        private var instance: FavoriteViewModelFactory? = null
        fun getInstance(repository: FavoriteRepository): FavoriteViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: FavoriteViewModelFactory(repository)

            }.also { instance = it }
    }
}
