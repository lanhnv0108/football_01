package com.sun.uefascore.screen.teamdetail

import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.model.Team
import com.sun.uefascore.utils.BasePresenter
import java.lang.Exception

interface TeamDetailContract {
    interface View {
        fun getTeamDetailSuccess(team: MutableList<Team>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getTeaDetail(id: Int)
    }
}
