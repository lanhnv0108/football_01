package com.sun.uefascore.data.source.repository

import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.source.FixtureDataSource
import com.sun.uefascore.data.source.remote.FixtureRemoteDataSource
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener

class FixtureRepository private constructor(
    private val remote: FixtureDataSource.Remote
) {

    fun getFixture(
        date: String,
        season: String,
        listener: OnFetchDataJsonListener<MutableList<Fixture>>
    ) {
        remote.getFixture(date, season, listener)
    }

    fun getAllFixture(
        season: String,
        listener: OnFetchDataJsonListener<MutableList<Fixture>>
    ) {
        remote.getAllFixture(season, listener)
    }

    fun getSeason(
        listener: OnFetchDataJsonListener<MutableList<String>>
    ){
        remote.getSeason(listener)
    }

    private object Holder {
        val INSTANCE = FixtureRepository(
            remote = FixtureRemoteDataSource.instance
        )
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}
