package com.sun.uefascore.screen.topscorers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.uefascore.R
import com.sun.uefascore.data.model.TopScorer
import com.sun.uefascore.data.source.repository.TopScorerRepository
import com.sun.uefascore.screen.playerdetail.PlayerDetailFragment
import com.sun.uefascore.screen.topscorers.adapter.TopScorersAdapter
import com.sun.uefascore.utils.Constant
import com.sun.uefascore.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.fragment_top_scorers.*
import java.lang.Exception

class TopScorersFragment : Fragment(), TopScorersContract.View,
    OnItemRecyclerViewClickListener<TopScorer> {

    private val presenter by lazy {
        TopScorersPresenter(TopScorerRepository.instance)
    }
    private val adapter: TopScorersAdapter by lazy {
        TopScorersAdapter()
    }
    private var season: String? = null

    fun updateSeason(season: String) {
        this.season = season
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_scorers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onGetTopScorerSuccess(topScorers: MutableList<TopScorer>) {
        adapter.updateData(topScorers)
    }

    override fun onError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickListener(item: TopScorer?) {
        item?.player?.let {
            PlayerDetailFragment.newInstance(it)
                .show(this@TopScorersFragment.childFragmentManager, null)
        }
    }

    private fun initView() {
        recyclerViewTopScorers.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }

    private fun initData() {
        presenter.run {
            setView(this@TopScorersFragment)
            season?.let { getTopScorer(it) }
        }
    }

    companion object {
        fun newInstance() = TopScorersFragment()
    }
}
