package com.sun.uefascore.screen.standing

import com.sun.uefascore.data.model.StandingLeague
import com.sun.uefascore.utils.BasePresenter
import java.lang.Exception

interface StandingContract {

    interface View {

        fun onGetStandingLeagueSuccess(standingLeague: StandingLeague)
        fun onError(exception: Exception)
    }

    interface Presenter : BasePresenter<View> {

        fun getStandingLeague(season : String)
    }
}
