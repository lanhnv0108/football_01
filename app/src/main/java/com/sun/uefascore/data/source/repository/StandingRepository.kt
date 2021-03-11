package com.sun.uefascore.data.source.repository

import com.sun.uefascore.data.model.StandingLeague
import com.sun.uefascore.data.source.StandingDataSource
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.remote.StandingRemoteDataSource

class StandingRepository private constructor(private val remote: StandingDataSource.Remote) {

    private object Holder {
        val INSTANCE = StandingRepository(remote = StandingRemoteDataSource.instance)
    }

    fun getStandingLeague(season: String, listener: OnFetchDataJsonListener<StandingLeague>) {
        remote.getDataStandingLeague(season, listener)
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}
