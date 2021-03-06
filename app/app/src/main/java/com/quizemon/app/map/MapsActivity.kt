package com.quizemon.app.map

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.quizemon.R
import com.quizemon.app.map.Types.QuizCoordinate
import com.quizemon.app.map.services.MapsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private val TAG = MapsActivity::class.java!!.getSimpleName()
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val mapservice by lazy { MapsService() }
    var disposable: Disposable? = null
    private lateinit var map: GoogleMap
    var coordinates: MutableList<QuizCoordinate> = mutableListOf<QuizCoordinate>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = false
        setUpMap()
        //val sydney = LatLng(-33.865143, 151.209900)
        //map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        Log.i(TAG,"Finish fetching moherfucker")
        test(22.00, 22.99)
        //val sydney = LatLng(-34.00, 151.00)
        //placeMarkerOnMap(sydney)
        Log.i(TAG,"markers added")

    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        map.addMarker(markerOptions)
    }

    private fun test(longitude: Double, latitude: Double) {
        Log.i(TAG,"in test")
        disposable = mapservice.fetchQuizCoordinates(22.00, 24.99)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { retrievedCoordinates ->
                            addMarkers(retrievedCoordinates)
                        },
                        { e ->

                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun addMarkers(retrievedCoordinates: MutableList<QuizCoordinate>) {
        Log.i(TAG,"adding markers ******" + retrievedCoordinates[0].longitude + "*****" + retrievedCoordinates[0].latitude)

        for(coordinate in retrievedCoordinates) {
            val marker = LatLng(coordinate.longitude, coordinate.latitude)
            placeMarkerOnMap(marker)
        }

        coordinates = retrievedCoordinates
    }
}
