// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codelabs.buildyourfirstmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.MarkerOptions
import com.google.codelabs.buildyourfirstmap.place.Place
import com.google.codelabs.buildyourfirstmap.place.PlaceRenderer
import com.google.codelabs.buildyourfirstmap.place.PlacesReader
import com.google.maps.android.clustering.ClusterManager

class MainActivity : AppCompatActivity() {
    val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
            //criar nova instância do MarkerInfoWindowAdapter
            //googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
        }

    }

    /*private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
            )
        }
    }*/
    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
                    .icon(simplePinIcon)
            )

            // definir o "place" como uma "tag" para que possa ser referenciado pelo MarkerInfoWindowAdapter
            marker?.tag = place
        }

    }

    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.colorPrimary)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_directions_bike_black_24dp, color)
    }

    private val simplePinIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_pin_place_simple, color)
    }

    private val restaurantIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.colorAccent)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_restaurant, color)
    }

    /**
     * Adiciona os marcadores do Cluster
     */
    private fun addClusteredMarkers(googleMap: GoogleMap) {
        // Criar Cluster Manager e renderizar
        val clusterManager = ClusterManager<Place>(this, googleMap)
        clusterManager.renderer =
            PlaceRenderer(
                this,
                googleMap,
                clusterManager
            )

        // colocar informação costum no adaptador
        clusterManager.markerCollection.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))

        // Adicionar os locais ao Cluster
        clusterManager.addItems(places)
        clusterManager.cluster()

        // Colocar o ClusterManager como OnCameraIdleListener para
        // que possa fazer o re-cluster dos locais quando fazemos zoom-out
        googleMap.setOnCameraIdleListener {
            clusterManager.onCameraIdle()
        }
    }



}
