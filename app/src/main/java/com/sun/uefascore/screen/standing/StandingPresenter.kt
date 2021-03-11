package com.sun.uefascore.screen.standing

import com.sun.uefascore.data.model.StandingLeague
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.repository.StandingRepository

class StandingPresenter(private val standingRepository: StandingRepository?) :
    StandingContract.Presenter {

    private var view: StandingContract.View? = null

    override fun onStart() {
    }

    override fun onStop() = Unit

    override fun setView(view: StandingContract.View?) {
        this.view = view
    }

    override fun getStandingLeague(season: String) {
        standingRepository?.getStandingLeague(season,
            object : OnFetchDataJsonListener<StandingLeague> {

                override fun onSuccess(data: StandingLeague) {
                    view?.onGetStandingLeagueSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    exception?.let { view?.onError(it) }
                }
            })
    }
}
