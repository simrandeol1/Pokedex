package com.example.kotlin.view.list

import androidx.lifecycle.ViewModel
import com.example.kotlin.data.PokemonListRepository
import com.example.kotlin.data.model.PokemonDetailResult

class DetailViewModel: ViewModel() {

    suspend fun getDetails(name: String): PokemonDetailResult{
        return PokemonListRepository.getPokemonDetailResults(name)
    }
}