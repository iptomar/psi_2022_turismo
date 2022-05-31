package com.google.codelabs.buildyourfirstmap

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "MainActivity"
private const val TAG2 = "MainActivity"
private const val INITIAL_DESCRIPTION = "Introduza uma descrição"
private const val INITIAL_BAR = 50
class AddPlace : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var tvCampoNome: TextView
    private lateinit var tvCampoLatitude: TextView
    private lateinit var tvcampoLongitude: TextView
    private lateinit var tvcampoDescricao: TextView
    private lateinit var tvcampoClassificacao: TextView
    private lateinit var tvCampoProximidade: TextView
    private lateinit var buttonAdd: Button
    private lateinit var buttonCancel: Button
    private lateinit var seekBarTip: SeekBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_add_place, container, false)

        tvCampoNome = view.findViewById(R.id.tvCampoNome)
        tvCampoLatitude = view.findViewById(R.id.tvCampoLatitude)
        tvcampoLongitude = view.findViewById(R.id.tvcampoLongitude)
        tvcampoClassificacao = view.findViewById(R.id.tvcampoClassificacao)
        tvCampoProximidade = view.findViewById(R.id.tvCampoProximidade)
        tvcampoDescricao = view.findViewById(R.id.tvcampoDescricao)
        buttonAdd = view.findViewById(R.id.buttonAdd)
        buttonCancel = view.findViewById(R.id.buttonCancel)
        seekBarTip = view.findViewById(R.id.seekBarTip)

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


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_place, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddPlace.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddPlace().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}