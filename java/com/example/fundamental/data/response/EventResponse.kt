package com.example.fundamental.data.response

import android.os.Parcelable
import com.example.fundamental.data.database.Favorite
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class EventResponse(
	@field:SerializedName("listEvents")
	val listEvents: List<ListEventsItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
data class ListEventsItem(

	@field:SerializedName("summary")
	val summary: String,

	@field:SerializedName("mediaCover")
	val mediaCover: String,

	@field:SerializedName("registrants")
	val registrants: Int,

	@field:SerializedName("imageLogo")
	val imageLogo: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("ownerName")
	val ownerName: String,

	@field:SerializedName("cityName")
	val cityName: String,

	@field:SerializedName("quota")
	val quota: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("beginTime")
	val beginTime: String,

	@field:SerializedName("endTime")
	val endTime: String,


	@field:SerializedName("category")
	val category: String
) : Parcelable

fun Event.toEntity(): Favorite {
	return Favorite(
		id = id,
		name = name,
		summary = summary,
		description = description,
		beginTime = beginTime,
		endTime = endTime,
		mediaCover = mediaCover,
		imageLogo = imageLogo,
		link = link,
		ownerName = ownerName,
		cityName = cityName,
		category = category,
		quota = quota,
		registrants = registrants
	)
}