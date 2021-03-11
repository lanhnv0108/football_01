package com.sun.uefascore.data.source

import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import java.util.*

interface FixtureDataSource {

    interface Remote {

        fun getFixture(
            date: String,
            season: String,
            listener: OnFetchDataJsonListener<MutableList<Fixture>>
        )

        fun getAllFixture(
            season: String,
            listener: OnFetchDataJsonListener<MutableList<Fixture>>
        )
    }
}
