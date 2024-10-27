package com.example.fundamental.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fundamental.ui.FavoriteViewModel


class UpdateFavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {
    fun getAllFavorite(): LiveData<List<Favorite>> = repository.getAllFavorite()
}
