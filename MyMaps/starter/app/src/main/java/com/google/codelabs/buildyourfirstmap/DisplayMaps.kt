
package com.google.codelabs.buildyourfirstmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.codelabs.buildyourfirstmap.place.Place
import com.google.codelabs.buildyourfirstmap.place.PlaceRenderer
import com.google.codelabs.buildyourfirstmap.place.PlacesReader
import com.google.maps.android.clustering.ClusterManager

class DisplayMaps : AppCompatActivity() {
    val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    //icons para colocar no mapa
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            // fazer o zoom no para para nao aparecer o mundo inteiro
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                places.forEach { bounds.include(it.latLng) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 150))
                //adicionar os marcadores adicionados no ficheiro json
                addMarkers(googleMap)
            }
        }

    }

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

        //Criar circulo em volta do item clicado
        /*   clusterManager.setOnClusterItemClickListener { item ->
               addCircle(googleMap, item)
               return@setOnClusterItemClickListener false
           }*/


        // Colocar o ClusterManager como OnCameraIdleListener para
        // que possa fazer o re-cluster dos locais quando fazemos zoom-out
        googleMap.setOnCameraIdleListener {
            //colocar os marcadores opacos quando a câmara pára de mover - nao esta a funcionar bem
            clusterManager.markerCollection.markers.forEach { it.alpha = 1.0f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 1.0f }

            // fazer o re-cluster
            clusterManager.onCameraIdle()
        }

        //colocar os markers translucidos quando se movimenta o mapa - nao esta a funcionar bem
        googleMap.setOnCameraMoveStartedListener {
            clusterManager.markerCollection.markers.forEach { it.alpha = 0.3f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 0.3f }
        }
    }


    // private var circle: Circle? = null


    //adicionar circulo a volta do sitio clicado

/*    private fun addCircle(googleMap: GoogleMap, item: Place) {
        circle?.remove()
        circle = googleMap.addCircle(
            CircleOptions()
                .center(item.latLng)
                .radius(1000.0)
                .fillColor(ContextCompat.getColor(this, R.color.colorPrimaryTranslucent))
                .strokeColor(ContextCompat.getColor(this, R.color.colorPrimary))
        )
    }*/




}