package com.sun.uefascore.utils

import com.sun.uefascore.BuildConfig

object Constant {
    const val BASE_URL = "https://api-football-v1.p.rapidapi.com/v3/"
    const val BASE_API_KEY = "?api_key=" + BuildConfig.API_KEY
    const val BASE_DATE = "?date="
    const val BASE_LEAGUE = "&league=2"
    const val BASE_LEAGUE_ALL = "?league=2"
    const val BASE_SEASON = "&season="
    const val DAY_FORMAT = "yyyy-MM-dd"
    const val DAY_FORMAT_FORWARD = "yyyy/MM/dd"
    const val RESPONSE = "response"
    const val TYPE_HEADER = 1
    const val TYPE_ITEM = 2
    const val BASE_ID = "id="
    const val BASE_TEAM = "team="
    const val SEARCH = "search="
}
