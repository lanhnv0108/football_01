package com.sun.uefascore.screen.fixtures.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.uefascore.R
import com.sun.uefascore.data.model.Fixture

class FixtureAllAdapter : RecyclerView.Adapter<FixtureAllViewHolder>() {

    private var onItemClickedHome: ((Fixture) -> Unit)? = null
    private var onItemClickedAway: ((Fixture) -> Unit)? = null
    private val fixtures = mutableListOf<Fixture>()

    fun updateData(fixture: MutableList<Fixture>?) {
        fixture?.let {
            fixtures.clear()
            fixtures.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun registerHomeClickListener(onItemClickedHome: ((Fixture) -> Unit)?) {
        this.onItemClickedHome = onItemClickedHome
    }

    fun registerAwayClickListener(onItemClickedAway: ((Fixture) -> Unit)?) {
        this.onItemClickedAway = onItemClickedAway
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureAllViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fixture_history, parent, false)
        return FixtureAllViewHolder(view).apply {
            registerAwayClickListener(onItemClickedAway)
            registerHomeClickListener(onItemClickedHome)
        }
    }

    override fun onBindViewHolder(holder: FixtureAllViewHolder, position: Int) {
        holder.binData(fixtures[position])
    }

    override fun getItemCount() = fixtures.size
}
