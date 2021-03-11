package com.sun.uefascore.screen.teamdetail

import com.sun.uefascore.data.model.Team
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.repository.FixtureRepository

class TeamDetailPresenter internal constructor(private val repository: FixtureRepository?) :
    TeamDetailContract.Presenter {

    private var view: TeamDetailContract.View? = null

    override fun getTeaDetail(id: Int) {
        repository?.getTeamDetail(
            id,
            object : OnFetchDataJsonListener<MutableList<Team>> {
                override fun onSuccess(data: MutableList<Team>) {
                    view?.getTeamDetailSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }

            }
        )
    }

    override fun onStart() {}

    override fun onStop() {}

    override fun setView(view: TeamDetailContract.View?) {
        this.view = view
    }
}