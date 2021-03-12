package com.sun.uefascore.data.source.remote

import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.source.FixtureDataSource
import com.sun.uefascore.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.uefascore.utils.Constant
import com.sun.uefascore.utils.TypeFootball
import com.sun.uefascore.utils.TypeModel

class FixtureRemoteDataSource private constructor() :
    FixtureDataSource.Remote {

    override fun getFixture(
        date: String,
        season: String,
        listener: OnFetchDataJsonListener<MutableList<Fixture>>
    ) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.FIXTURE.path +
                Constant.BASE_DATE + date +
                Constant.BASE_LEAGUE +
                Constant.BASE_SEASON +
                season
        GetJsonFromUrl(
            listener,
            TypeModel.FIXTURE
        ).execute(baseUrl)
    }

    override fun getAllFixture(
        season: String,
        listener: OnFetchDataJsonListener<MutableList<Fixture>>
    ) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.FIXTURE.path +
                Constant.BASE_LEAGUE_ALL +
                Constant.BASE_SEASON +
                season
        GetJsonFromUrl(
            listener,
            TypeModel.FIXTURE
        ).execute(baseUrl)
    }

    override fun getSeason(listener: OnFetchDataJsonListener<MutableList<String>>) {
        val baseUrl = Constant.BASE_URL +
                TypeFootball.LEAGUE.path +
                TypeFootball.SEASON.path
        GetJsonFromUrl(
            listener,
            TypeModel.SEASON
        ).execute(baseUrl)
    }

    private object Holder {
        val INSTANCE = FixtureRemoteDataSource()
    }

    companion object {
        val instance: FixtureRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
