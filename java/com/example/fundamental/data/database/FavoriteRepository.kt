package com.example.fundamental.data.database

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository (application: Application){
    private val mDao: Dao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mDao = db.Dao()
    }
    fun getAllFavorite(): LiveData<List<Favorite>> = mDao.getAllFavorite()
    fun insert(favorite: Favorite) {
        executorService.execute { mDao.insert(favorite) }
    }

    fun delete(favorite: Favorite) {
        executorService.execute { mDao.delete(favorite) }
    }
    fun update(favorite: Favorite) {
        executorService.execute { mDao.update(favorite) }
    }
    fun isItemExists(favorite: Int ): LiveData<Boolean>{
        return mDao.isItemExists(favorite)

    }
}
