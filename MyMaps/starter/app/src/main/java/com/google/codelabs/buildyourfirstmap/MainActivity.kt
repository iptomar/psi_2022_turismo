
package com.google.codelabs.buildyourfirstmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var tvCampoNome: EditText
    private lateinit var tvCampoLatitude: EditText
    private lateinit var tvcampoLongitude: EditText
    private lateinit var tvcampoClassificacao: EditText
    private lateinit var tvCampoProximidade: EditText
    private lateinit var tvcampoDescricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_maps)

        tvCampoNome = findViewById(R.id.tvCampoNome)
        tvCampoLatitude = findViewById(R.id.tvCampoLatitude)
        tvcampoLongitude = findViewById(R.id.tvcampoLongitude)
        tvcampoClassificacao = findViewById(R.id.tvcampoClassificacao)
        tvCampoProximidade = findViewById(R.id.tvCampoProximidade)
        tvcampoDescricao = findViewById(R.id.tvcampoDescricao)
    }


}
