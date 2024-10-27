package com.example.fundamental.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fundamental.data.database.FavoriteRepository
import com.example.fundamental.data.database.FavoriteViewModelFactory
import com.example.fundamental.data.response.toEntity
import com.example.fundamental.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var eventAdapter: EventAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FavoriteRepository(application)
        val detailViewModel by viewModels<DetailViewModel> {
            FavoriteViewModelFactory.getInstance(repository)
        }
        detailViewModel.eventDetail.observe(this, Observer { detailResponse ->
            detailResponse?.let{
                val event = it.event

                binding.tvTitle.text = event.name
                binding.tvSummary.text = event.summary
                binding.tvDescription.text = HtmlCompat.fromHtml(event.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                Glide.with(this)
                    .load(event.imageLogo)
                    .into(binding.ivPicture)

                binding.tvCity.text = event.cityName
                binding.tvOwner.text = "Penyelenggara : ${event.ownerName}"
                binding.tvCat.text = event.category
                binding.tvQuota.text = "Quota: ${event.quota}"
                binding.tvRegistrans.text = "Registrans: ${event.registrants}"
                binding.tvTime.text =  "${event.beginTime} - ${event.endTime}"

                val remainingQuota = event.quota - event.registrants
                binding.tvRemainingQuota.text = "remaining quota: $remainingQuota"
            }?:run{
                Log.e("DetailActivity", "Event detail is null")
                Toast.makeText(this, "Failed to load event details", Toast.LENGTH_SHORT).show()
            }
            detailViewModel.isLoading.observe(this) { isLoading ->
                showLoading(isLoading)
            }
        }

        )
        var isFavorited = false
        val eventId = intent.getIntExtra("EVENT_ID", 0)
        if (eventId != 0){
            detailViewModel.fetchDetailEvent(eventId.toString())
            observeEventDetail()
        }else{
            Toast.makeText(this, "Invalid Event ID", Toast.LENGTH_SHORT).show()
            finish()
        }
        detailViewModel.isItemExists(eventId).observe(this) { isFavorite ->
            updateFabIcon(isFavorite)
          isFavorited = isFavorite
        }


        binding.btnFavorite.setOnClickListener{
            val event = detailViewModel.eventDetail.value?.event
            if(event != null){
                if(isFavorited) {
                    detailViewModel.deleteFavorite(event.toEntity())
                    Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show()
                    updateFabIcon(isFavorited)
                }else{
                    detailViewModel.insertFavorite(event.toEntity())
                    Toast.makeText(this, "Saved to Favorites", Toast.LENGTH_SHORT).show()
                    updateFabIcon(isFavorited)
                }
            }
        }

        binding.btnRegis.setOnClickListener{
            val regUrl = detailViewModel.eventDetail.value?.event?.link
            if(!regUrl.isNullOrEmpty()){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(regUrl))
                startActivity(intent)
            }else{
                Toast.makeText(this, "Registration Link not Available", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun updateFabIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnFavorite.text = "Remove from Favorites"
        } else {
            binding.btnFavorite.text = "Save to Favorites"
        }
    }
    private fun showLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading == true) View.VISIBLE else View.GONE
        binding.scrollView.visibility  = if (loading) View.GONE else View.VISIBLE
    }

    private fun observeEventDetail() {

    }
}
