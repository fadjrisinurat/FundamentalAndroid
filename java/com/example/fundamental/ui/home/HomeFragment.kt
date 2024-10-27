package com.example.fundamental.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamental.databinding.FragmentHomeBinding
import com.example.fundamental.ui.EventAdapter
import com.example.fundamental.ui.upcoming.UpcomingViewModel
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Initialize ViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Initialize the adapter with an empty list
        eventAdapter = EventAdapter(listOf())

        // Set up the RecyclerView
        with(binding.rvAll) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }

        observeEvents()

        return binding.root
    }

    private fun observeEvents() {
        // Your logic to observe data and update the adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}