package com.example.fundamental.setting

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamental.data.database.UpdateFavoriteViewModel
import com.example.fundamental.ui.MainViewModel


class ViewModelFactory(private val pref: SettingPreferences,val application: Application) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SettingViewModel::class.java) ->{
                SettingViewModel(pref) as T
            }


            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context,application: Application): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(SettingPreferences.getInstance(context.dataStore),application)
            }.also { instance = it }
    }
    }

