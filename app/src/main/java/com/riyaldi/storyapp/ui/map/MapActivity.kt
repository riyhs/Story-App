package com.riyaldi.storyapp.ui.map

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.data.Result
import com.riyaldi.storyapp.data.remote.response.stories.Story
import com.riyaldi.storyapp.databinding.ActivityMapBinding
import com.riyaldi.storyapp.utils.ViewModelFactory

class MapActivity : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding

    private val mapViewModel: MapViewModel by viewModels{
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        mapViewModel.getStoriesWithLocation().observe(this) {
            if (it != null) {
                when(it) {
                    is Result.Success -> {
                        addManyMarker(it.data.listStory)
                    }
                    is Result.Loading -> {
//                        showLoading
                    }
                    is Result.Error -> {
                        Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private val boundsBuilder = LatLngBounds.Builder()

    private fun addManyMarker(stories: List<Story>) {
        stories.forEach { tourism ->
            val latLng = LatLng(tourism.lat, tourism.lon)
            mMap.addMarker(MarkerOptions().position(latLng).title(tourism.name))
            boundsBuilder.include(latLng)
        }

        val bounds: LatLngBounds = boundsBuilder.build()

        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                30
            )
        )
    }
}