package com.google.codelabs.buildyourfirstmap
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.codelabs.buildyourfirstmap.place.Place


class MarkerInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter{
    override fun getInfoContents(marker: Marker): View? {
        // 1. ir buscar a tag
        val place = marker?.tag as? Place ?: return null

        // 2.  inflate view e definir titulo, morada e rating
        val view = LayoutInflater.from(context).inflate(
            R.layout.marker_info_contents, null
        )
        view.findViewById<TextView>(
            R.id.text_view_title
        ).text = place.name
        view.findViewById<TextView>(
            R.id.text_view_address
        ).text = place.address
        view.findViewById<TextView>(
            R.id.text_view_rating
        //deve estar em portugues ou inglês?
        ).text = "Rating: %.2f".format(place.rating)

        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
        //retornar null para indicar que a janela por defeito deve ser usada
        //colocação incorreta da tag
    }

}