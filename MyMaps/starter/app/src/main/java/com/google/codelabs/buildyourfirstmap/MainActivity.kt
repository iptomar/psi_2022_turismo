
package com.google.codelabs.buildyourfirstmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
private const val TAG = "MainActivity"
private const val TAG2 = "MainActivity"
private const val INITIAL_DESCRIPTION = "Introduza uma descrição"
private const val INITIAL_BAR = 50

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_maps)

        val tvCampoNome: TextView = findViewById(R.id.tvCampoNome)
        val tvCampoLatitude: TextView = findViewById(R.id.tvCampoLatitude)
        val tvcampoLongitude: TextView = findViewById(R.id.tvcampoLongitude)
        val tvcampoClassificacao: TextView = findViewById(R.id.tvcampoClassificacao)
        val tvCampoProximidade: TextView = findViewById(R.id.tvCampoProximidade)
        val tvcampoDescricao: TextView = findViewById(R.id.tvcampoDescricao)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonCancel: Button = findViewById(R.id.buttonCancel)
        val seekBarTip: SeekBar = findViewById(R.id.seekBarTip)


        seekBarTip.progress = INITIAL_BAR
        tvcampoClassificacao.text = "$INITIAL_BAR%"
        tvcampoDescricao.text = INITIAL_DESCRIPTION
        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG2, "onProgressChanged $progress")
                tvcampoClassificacao.text = "$progress%"
                Log.i(TAG, "Localização ${tvCampoNome.text}")
                Log.i(TAG, "Latitude ${tvCampoLatitude.text}")
                Log.i(TAG, "Longitude ${tvcampoLongitude.text}")
                Log.i(TAG, "Classificação ${tvcampoClassificacao.text}")
                Log.i(TAG, "Proximidade ${tvCampoProximidade.text}")
                Log.i(TAG, "Descrição ${tvcampoDescricao.text}")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        buttonAdd.setOnClickListener{
            Log.i(TAG, "Localização ${tvCampoNome.text}")
            Log.i(TAG, "Latitude ${tvCampoLatitude.text}")
            Log.i(TAG, "Longitude ${tvcampoLongitude.text}")
            Log.i(TAG, "Classificação ${tvcampoClassificacao.text}")
            Log.i(TAG, "Proximidade ${tvCampoProximidade.text}")
            Log.i(TAG, "Descrição ${tvcampoDescricao.text}")


        }
    }
}
