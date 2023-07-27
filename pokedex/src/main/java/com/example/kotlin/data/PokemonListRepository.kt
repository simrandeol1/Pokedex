package com.example.kotlin.data

import android.net.Uri
import com.example.kotlin.data.model.PokemonDetailResult
import com.example.kotlin.data.model.PokemonListResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PokemonListRepository {

    private val api: PokemonApi? = ApiClient.api()

    suspend fun getPokemonListResults(page: Int): PokemonListResult =
        withContext(Dispatchers.IO) {
            val response = api?.pokemonList(page * PER_PAGE)
            response?.body()?.let {
                PokemonListResult(
                    nextPage = getPageFromUrl(it.next),
                    prevPage = getPageFromUrl(it.previous),
                    results = it.results
                )
            } ?: PokemonListResult(null, null, emptyList())
        }

    suspend fun getPokemonDetailResults(name: String): PokemonDetailResult =
        withContext(Dispatchers.IO) {
            val response = api?.pokemonDetail(name)
            response?.body()?.let {
                PokemonDetailResult(
                    weight = it.weight,
                    height = it.height,
                    id = it.id
                )
            }?: PokemonDetailResult(0,0,0)
        }

    private fun getPageFromUrl(urlString: String?): Int? {
        return urlString?.let {
            Uri.parse(it).getQueryParameter(PARAM_OFFSET)?.toInt()?.div(PER_PAGE)
        }
    }


    private const val PARAM_OFFSET = "offset"
    const val PER_PAGE = 20
}