
package com.google.codelabs.buildyourfirstmap.place

import android.content.Context
import com.google.codelabs.buildyourfirstmap.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader

class PlacesReader(private val context: Context) {

    // converte JSNON para objecto Place
    private val gson = Gson()

    //InputStream que representa o ficheiro com os locais (places.json)
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    // retorna uma lista de "Places" que leu do ficheiro places.json
    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
}