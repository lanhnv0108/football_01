package com.sun.uefascore.data.source

import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener

interface StandingDataSource {
    /**
     *  Local
     */
    interface Local

    /**
     *  Remote
     */
    interface Remote {
        fun <T> getDataStandingLeague(season: String, listener: OnFetchDataJsonListener<T>)
    }
}
