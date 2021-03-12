package com.sun.uefascore.data.source

import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener

interface TeamDataSource {
    /**
     *  Local
     */
    interface Local

    /**
     *  Remote
     */
    interface Remote {

        fun <T> getDataTeamById(
            season: String,
            idTeam: String,
            listener: OnFetchDataJsonListener<T>
        )

        fun <T> getDataPlayersById(
            season: String,
            idTeam: String,
            listener: OnFetchDataJsonListener<MutableList<T>>
        )

        fun <T> getDataTeamByName(
            name: String,
            listener: OnFetchDataJsonListener<T>
        )
    }
}
