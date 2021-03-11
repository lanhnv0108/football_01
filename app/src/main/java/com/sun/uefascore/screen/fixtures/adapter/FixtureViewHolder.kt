package com.sun.uefascore.screen.fixtures.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.uefascore.data.model.Fixture
import kotlinx.android.synthetic.main.item_fixture_of_day.view.*

class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var onItemClickedHome: ((Fixture) -> Unit)? = null
    private var onItemClickedAway: ((Fixture) -> Unit)? = null

    @SuppressLint("SetTextI18n")
    fun binData(fixture: Fixture) {
        itemView.apply {
            textViewNameHome.text = fixture.home?.name
            textViewNameAway.text = fixture.away?.name
            textViewGoals.text =
                fixture.goals?.home.toString() + " - " + fixture.goals?.away.toString()
            Glide.with(context)
                .load(fixture.home?.logo)
                .into(imageViewTeamHome)
            Glide.with(context)
                .load(fixture.away?.logo)
                .into(imageViewTeamWay)
            imageViewTeamHome.setOnClickListener {
                onItemClickedHome?.let { it -> it(fixture) }
            }
            imageViewTeamWay.setOnClickListener {
                onItemClickedAway?.let { it -> it(fixture) }
            }
        }
    }

    fun registerHomeClickListener(onItemClickedHome: ((Fixture) -> Unit)?) {
        this.onItemClickedHome = onItemClickedHome
    }

    fun registerAwayClickListener(onItemClickedAway: ((Fixture) -> Unit)?) {
        this.onItemClickedAway = onItemClickedAway
    }
}
