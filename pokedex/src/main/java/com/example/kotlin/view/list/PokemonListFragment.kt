package com.example.kotlin.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.interfaces.NameClickInterface
import com.example.kotlin.R
import com.example.kotlin.data.model.PokemonListEntryResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PokemonListFragment(val nameClickInterface: NameClickInterface) : Fragment() {

    private val adapter: PokemonPagingListAdapter?
        get() = view?.findViewById<RecyclerView>(R.id.pokemon_list)?.adapter as? PokemonPagingListAdapter

    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        view.findViewById<RecyclerView>(R.id.pokemon_list).apply {
            adapter = PokemonPagingListAdapter(nameClickInterface)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch { viewModel.pokemonFlow.collectLatest { populateList(it) } }
    }

    private suspend fun populateList(results: PagingData<PokemonListEntryResult>) {
        adapter?.submitData(results)

    }

    companion object {
        fun newInstance(nameClickInterface: NameClickInterface): Fragment {
            return PokemonListFragment(nameClickInterface)
        }
    }
}