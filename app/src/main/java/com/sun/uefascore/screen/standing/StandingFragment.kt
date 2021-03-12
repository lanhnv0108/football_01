package com.sun.uefascore.screen.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.uefascore.R
import com.sun.uefascore.data.model.StandingLeague
import com.sun.uefascore.data.model.Team
import com.sun.uefascore.data.source.repository.StandingRepository
import com.sun.uefascore.screen.standing.adapter.StandingGroupAdapter
import com.sun.uefascore.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.fragment_standing.*
import kotlin.Exception

class StandingFragment : Fragment(),
    StandingContract.View,
    OnItemRecyclerViewClickListener<Team> {

    private var season = ""
    private val presenter by lazy {
        StandingPresenter(StandingRepository.instance)
    }
    private val adapter: StandingGroupAdapter by lazy {
        StandingGroupAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onGetStandingLeagueSuccess(standingLeague: StandingLeague) {
        adapter.updateData(standingLeague.standingGroups)
    }

    override fun onError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickListener(item: Team?) {
    }

    private fun initView() {
        recyclerViewStandingGroup.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }

    private fun initData() {
        presenter.run {
            setView(this@StandingFragment)
            getStandingLeague(season)
        }
    }

    companion object {
        fun newInstance() = StandingFragment()
    }
}
