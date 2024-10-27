package com.example.fundamental.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fundamental.R
import com.example.fundamental.databinding.ActivityMainBinding
import com.example.fundamental.setting.SettingPreferences
import com.example.fundamental.setting.ViewModelFactory
import com.example.fundamental.setting.dataStore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var switchTheme: SwitchMaterial
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView adapter
        val recyclerViewAdapter = RecyclerViewAdapter()
        recyclerViewAdapter.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(eventId: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("EVENT_ID", eventId)
                startActivity(intent)
            }
        })

        // Initialize navigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)


    }
}
