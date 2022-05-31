
package com.google.codelabs.buildyourfirstmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //@SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_maps)

        val tvCampoNome: TextView = findViewById(R.id.tvCampoNome)
        val tvCampoLatitude: TextView = findViewById(R.id.tvCampoLatitude)
        val tvcampoLongitude: TextView = findViewById(R.id.tvcampoLongitude)
        val tvcampoClassificacao: TextView = findViewById(R.id.tvcampoClassificacao)
        val tvCampoProximidade: TextView = findViewById(R.id.tvCampoProximidade)
        val tvcampoDescricao: TextView = findViewById(R.id.tvcampoDescricao)
    }


}

