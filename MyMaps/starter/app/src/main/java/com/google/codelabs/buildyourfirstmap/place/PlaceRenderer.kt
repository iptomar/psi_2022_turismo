package com.google.codelabs.buildyourfirstmap.place
import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.codelabs.buildyourfirstmap.BitmapHelper
import com.google.codelabs.buildyourfirstmap.R
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * Quando temos demasiados locais no mesmo sítio queremos
 * que apareça um icon que junte esses items (Cluster Item)
 * renderiza os objectos do tipo "Place"
 */
class PlaceRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<Place>
) : DefaultClusterRenderer<Place>(context, map, clusterManager) {

    /**
     * Icon que vamos utilizar sempre que houver para cada Cluster item
     * Substituir o icon da bicicleta por outro Icon qualquer (res/drawable - vectors)
     */
    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(context,
            R.color.colorPrimary
        )
        BitmapHelper.vectorToBitmap(
            context,
            R.drawable.ic_directions_bike_black_24dp,
            color
        )
    }

    /**
     * Método chamado antes do item do Cluster ser chamado
     * Definição das propriedades do Marker (titulo, posicao e icon)
     */
    override fun onBeforeClusterItemRendered(
        item: Place,
        markerOptions: MarkerOptions
    ) {
        markerOptions.title(item.name)
            .position(item.latLng)
            .icon(bicycleIcon)
    }

    /**
     * Método chamado após o item (marker) ser renderizado
     * Definiçao das propriedades do Marker (propriedades da tag)
     */
    override fun onClusterItemRendered(clusterItem: Place, marker: Marker) {
        marker.tag = clusterItem
    }
}
