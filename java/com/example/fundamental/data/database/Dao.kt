package com.example.fundamental.data.database

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
@Dao
interface Dao  {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)
    @Update
    fun update(favorite: Favorite )
    @Delete
    fun delete(favorite: Favorite)
    @Query("SELECT * from Favorite ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<Favorite>>
    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE id = :id)")
    fun isItemExists(id: Int): LiveData<Boolean>
}
