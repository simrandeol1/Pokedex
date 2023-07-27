package com.example.kotlin.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.kotlin.data.PokemonListRepository

class PokemonListViewModel : ViewModel() {

    val pokemonFlow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(PokemonListRepository.PER_PAGE)
    ) {
        PokemonListPagingSource()
    }.flow.cachedIn(viewModelScope)

}