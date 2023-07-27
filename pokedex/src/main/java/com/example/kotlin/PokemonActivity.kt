package com.example.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.interfaces.NameClickInterface
import com.example.kotlin.view.list.PokemonListFragment

class PokemonActivity : AppCompatActivity(), NameClickInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(android.R.id.content, PokemonListFragment.newInstance(this)).commit()
        }
    }

    override fun showDetailOnClick(name: String) {
        supportFragmentManager.beginTransaction().replace(android.R.id.content, PokemonDetailFragment.newInstance(name)).addToBackStack("").commit()
    }
}