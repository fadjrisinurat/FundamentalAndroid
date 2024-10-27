package com.example.fundamental.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.example.fundamental.data.response.ListEventsItem
import com.google.gson.annotations.SerializedName

@Entity
@Parcelize

class Favorite (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "summary")
    var summary: String? = null,

    @ColumnInfo(name = "mediaCover")
    var mediaCover: String? = null,

    @ColumnInfo(name = "ownerName")
    var ownerName: String? = null,

    @ColumnInfo(name = "cityName")
    var cityName: String? = null,

    @ColumnInfo(name = "quota")
    var quota: Int? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "beginTime")
    var beginTime: String? = null,

    @ColumnInfo(name = "endTime")
    var endTime: String? = null,

    @ColumnInfo(name = "category")
    var category: String? = null,

    @ColumnInfo(name = "link")
    var link: String? = null,

    @ColumnInfo(name = "imageLogo")
    var imageLogo: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "registrants")
    var registrants: Int? = null,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false


) : Parcelable

fun Favorite.toListEventsItem(): ListEventsItem {
    return ListEventsItem(
        id = id,
        name = name!!,
        summary = summary!!,
        description = description!!,
        beginTime = beginTime!!,
        endTime = endTime!!,
        mediaCover = mediaCover!!,
        imageLogo = imageLogo!!,
        link = link!!,
        ownerName = ownerName!!,
        cityName = cityName!!,
        category = category!!,
        quota = quota!!,
        registrants = registrants!!
    )
}

fun List<Favorite>.toListEventsItems(): List<ListEventsItem> {
    return this.map { it.toListEventsItem() }
}
