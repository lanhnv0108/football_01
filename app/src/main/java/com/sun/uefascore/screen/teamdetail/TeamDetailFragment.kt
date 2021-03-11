package com.sun.uefascore.screen.teamdetail

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.sun.uefascore.data.model.Team
import com.sun.uefascore.data.source.repository.FixtureRepository
import java.lang.Exception

class TeamDetailFragment : Fragment(), TeamDetailContract.View {
    private val teamDetailPresenter: TeamDetailContract.Presenter by lazy {
        TeamDetailPresenter(
            FixtureRepository.instance
        )
    }

    override fun getTeamDetailSuccess(team: MutableList<Team>) {
    }

    override fun onError(exception: Exception?) {}

    companion object {
        private const val ARG_ID_TEAM_DETAIL = "ARG_ID_TEAM_DETAIL"

        fun newInstance(id: Int) = TeamDetailFragment().apply {
            arguments = bundleOf(ARG_ID_TEAM_DETAIL to id)
        }
    }
}