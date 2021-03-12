package com.sun.uefascore.data.source.repository

import com.sun.uefascore.data.model.Player
import com.sun.uefascore.data.model.PlayerDetail
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.TeamDataSource
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.remote.TeamRemoteDataSource

class TeamRepository private constructor(
    private val remote: TeamDataSource.Remote
) {

    private object Holder {
        val INSTANCE = TeamRepository(TeamRemoteDataSource.instance)
    }

    fun getTeamById(
        season: String,
        idTeam: String,
        listener: OnFetchDataJsonListener<TeamDetail>
    ) {
        remote.getDataTeamById(season, idTeam, listener)
    }

    fun getPlayersById(
        season: String,
        idTeam: String,
        listener: OnFetchDataJsonListener<MutableList<PlayerDetail>>
    ) {
        remote.getDataPlayersById(season, idTeam, listener)
    }

    fun getTeamByName(
        name: String,
        listener: OnFetchDataJsonListener<MutableList<TeamDetail>>
    ) {
        remote.getDataTeamByName(name, listener)
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}
