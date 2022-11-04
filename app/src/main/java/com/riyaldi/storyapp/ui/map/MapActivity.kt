package com.riyaldi.storyapp.ui.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.data.remote.response.stories.Story
import com.riyaldi.storyapp.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding

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

        val dicodingSpace = LatLng(-6.8957643, 107.6338462)
        mMap.addMarker(
            MarkerOptions()
                .position(dicodingSpace)
                .title("Dicoding Space")
                .snippet("Batik Kumeli No.50")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dicodingSpace, 15f))

        addManyMarker()
    }

    private val boundsBuilder = LatLngBounds.Builder()

    private fun addManyMarker() {
        val stories = listOf(
            Story("","Floating Market Lembang", "id", -6.8168954,107.6151046,"Floating Market Lembang", "photo"),
            Story("","The Great Asia Africa", "id",-6.8331128,107.6048483,"The Great Asia Africa", "photo"),
            Story("","Rabbit Town", "id",-6.8668408, 107.608081,"Rabbit Town", "photo"),
            Story("","Alun-Alun Kota Bandung", "id",-6.9218518,107.6025294,"Alun-Alun Kota Bandung", "photo"),
            Story("","Orchid Forest Cikole", "id",-6.780725, 107.637409,"Orchid Forest Cikole", "photo"),
        )
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
                300
            )
        )
    }
}