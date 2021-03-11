package com.sun.uefascore.data.source.remote

import com.sun.uefascore.data.source.StandingDataSource
import com.sun.uefascore.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.uefascore.utils.Constant
import com.sun.uefascore.utils.TypeModel

class StandingRemoteDataSource private constructor() : StandingDataSource.Remote {

    private object Holder {
        val INSTANCE = StandingRemoteDataSource()
    }

    override fun <T> getDataStandingLeague(season: String, listener: OnFetchDataJsonListener<T>) {
        val stringUrl = Constant.BASE_URL +
                STANDINGS +
                Constant.BASE_SEASON +
                season +
                Constant.BASE_LEAGUE
        GetJsonFromUrl(
            listener,
            TypeModel.STANDING_LEAGUE
        ).execute("https://api-football-v1.p.rapidapi.com/v3/standings?season=2020&league=2")
    }

    companion object {
        private const val STANDINGS = "standings?"
        val instance by lazy { Holder.INSTANCE }
    }
}
