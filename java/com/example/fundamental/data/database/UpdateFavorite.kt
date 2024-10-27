package com.example.fundamental.data.database

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.fundamental.R
import com.example.fundamental.data.response.Event
import com.example.fundamental.ui.FavoriteViewModel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import kotlinx.parcelize.Parcelize
class UpdateFavorite : AppCompatActivity() {

    private lateinit var favoriteViewModel: FavoriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_favorite)
        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)


        val event = intent.getParcelableExtra<Event>("EVENT_DATA")
        if (event != null) {
            saveFavorite(event)
        } else {
            Toast.makeText(this, "Failed to load event details", Toast.LENGTH_SHORT).show()
        }
    }
    }
    private fun saveFavorite(event: Event) {
        val favorite = Favorite(
            id = event.id,
            name = event.name,
            summary = event.summary,
            description = event.description,
            imageLogo = event.imageLogo,
            cityName = event.cityName,
            beginTime = event.beginTime,
            endTime = event.endTime
        )



    }



