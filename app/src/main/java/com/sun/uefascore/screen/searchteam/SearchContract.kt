package com.sun.uefascore.screen.searchteam

import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.utils.BasePresenter

interface SearchContract {
    interface View {
        fun getTeamByNameSuccess(team: MutableList<TeamDetail>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getTeamByName(name: String)
    }
}
