package com.example.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.data.model.PokemonDetailResult
import com.example.kotlin.view.list.DetailViewModel
import kotlinx.coroutines.launch

class PokemonDetailFragment(val name: String): Fragment(){

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokemon_detail_layout, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch { populateData(viewModel.getDetails(name)) }
    }

    private fun populateData(details: PokemonDetailResult){
        view?.findViewById<TextView>(R.id.weight_tv)?.text = details.weight.toString()
        view?.findViewById<TextView>(R.id.height_tv)?.text = details.height.toString()
        view?.findViewById<TextView>(R.id.id_tv)?.text = details.id.toString()
    }

    companion object {
        fun newInstance(name: String): Fragment {
            return PokemonDetailFragment(name)
        }
    }

}