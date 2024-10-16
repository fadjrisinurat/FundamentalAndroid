package com.example.fundamental.ui.upcoming

import android.content.Intent
import android.media.metrics.Event
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamental.databinding.FragmentHomeBinding
import com.example.fundamental.databinding.FragmentUpcomingBinding
import com.example.fundamental.ui.DetailActivity
import com.example.fundamental.ui.EventAdapter
import com.example.fundamental.ui.upcoming.UpcomingViewModel


class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventAdapter
    private lateinit var upcomingViewModel: UpcomingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)

        upcomingViewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)

        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(listOf())
        binding.rvHome.adapter = eventAdapter


        eventAdapter.setOnItemClickCallback(object: EventAdapter.OnItemClickCallback {
            override fun onItemClicked(eventId: String) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra("EVENT_ID", eventId)
                startActivity(intent)
            }
        })

        observeEvents()

        return binding.root

    }

    private fun observeEvents() {
        upcomingViewModel.upcomingEvents.observe(viewLifecycleOwner, Observer{ events->
            if (events != null && events.isNotEmpty()){
                eventAdapter.updateData(events)
            }else{
                Toast.makeText(requireContext(), "No Events available", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}