package com.example.fundamental.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamental.R
import com.example.fundamental.data.database.Favorite
import com.example.fundamental.data.database.FavoriteRepository
import com.example.fundamental.data.database.FavoriteViewModelFactory
import com.example.fundamental.data.database.UpdateFavoriteViewModel
import com.example.fundamental.data.database.toListEventsItems
import com.example.fundamental.databinding.FragmentFavoriteBinding
import com.example.fundamental.databinding.FragmentUpcomingBinding
import com.example.fundamental.setting.SettingViewModel
import com.example.fundamental.setting.ViewModelFactory
import com.example.fundamental.ui.upcoming.UpcomingViewModel

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var eventAdapter: EventAdapter
    private lateinit var FavoriteViewModel: FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val repository = FavoriteRepository(requireActivity()!!.application)
        val updateFavoriteViewModel by viewModels<FavoriteViewModel> {
            FavoriteViewModelFactory.getInstance(repository)
        }
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(listOf())
        binding.rvFavorite.adapter = eventAdapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(listOf())
        binding.rvFavorite.adapter = eventAdapter

        eventAdapter.setOnItemClickCallback(object: EventAdapter.OnItemClickCallback {
            override fun onItemClicked(eventId: String) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra("EVENT_ID", eventId)
                startActivity(intent)
            }
        })
        updateFavoriteViewModel.getFavorite().observe(viewLifecycleOwner){
            eventAdapter.updateData(it.toListEventsItems())
        }
    }
}