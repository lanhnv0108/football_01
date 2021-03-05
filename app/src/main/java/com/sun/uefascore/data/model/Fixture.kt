package com.sun.uefascore.data.model

data class Fixture(
    val home: Team?,
    val away: Team?,
    val goals: Goals?
)

object FixtureEntry {
    const val RESPONSE = "response"
    const val GOALS = "goals"
    const val HOME = "home"
    const val AWAY = "away"
    const val TEAMS = "teams"
}
