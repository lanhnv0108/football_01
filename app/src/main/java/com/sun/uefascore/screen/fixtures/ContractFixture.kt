package com.sun.uefascore.screen.fixtures

import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.model.Team
import com.sun.uefascore.utils.BasePresenter
import java.lang.Exception

interface ContractFixture {

    interface View {
        fun onGetFixtureSuccess(fixtures: MutableList<Fixture>)
        fun onGetAllFixtureSuccess(fixtures: MutableList<Fixture>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getFixture(date: String, season: String)
        fun getAllFixture(season: String)
    }
}
