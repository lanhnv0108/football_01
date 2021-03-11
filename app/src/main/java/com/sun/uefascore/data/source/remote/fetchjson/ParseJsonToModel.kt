package com.sun.uefascore.data.source.remote.fetchjson


import android.util.Log
import com.sun.uefascore.data.model.*
import org.json.JSONObject
import com.sun.uefascore.utils.TypeModel
import org.json.JSONArray

@Suppress("UNCHECKED_CAST")
class ParseJsonToModel {

    fun parseJsonToFixture(jsonObject: JSONObject?): Fixture? =
        jsonObject?.run {
            Fixture(
                date = getJSONObject(FixtureEntry.FIXTURE).getString(FixtureEntry.DATE),
                home = parseJsonToTeam(getJSONObject(FixtureEntry.TEAMS).getJSONObject(FixtureEntry.HOME)),
                away = parseJsonToTeam(getJSONObject(FixtureEntry.TEAMS).getJSONObject(FixtureEntry.AWAY)),
                goals = parseJsonToGoal(getJSONObject(FixtureEntry.GOALS))
            )
        }

    private fun parseJsonToTeam(jsonObject: JSONObject?): Team? =
        jsonObject?.run {
            Team(
                getInt(TeamEntry.ID),
                getString(TeamEntry.NAME),
                getString(TeamEntry.LOGO)
            )
        }

    private fun parseJsonToGoal(jsonObject: JSONObject?): Goals? =
        jsonObject?.run {
            Goals(
                getString(GoalsEntry.HOME),
                getString(GoalsEntry.AWAY)
            )
        }

    @Throws(Exception::class)
    fun parseJsonToStandingLeague(jsonObject: JSONObject?): StandingLeague? =
        jsonObject?.run {
            val parseDataWithJson = ParseDataWithJson()
            val standingGroup = parseDataWithJson.parseJson(
                jsonObject.toString(),
                TypeModel.STANDING_GROUP
            ) as MutableList<StandingGroup>
            StandingLeague(
                id = getInt(StandingLeagueEntry.ID),
                name = getString(StandingLeagueEntry.NAME),
                standingGroups = standingGroup
            )
        }

    @Throws(Exception::class)
    fun parseJsonToStandingGroup(jsonArray: JSONArray?): StandingGroup? =
        jsonArray?.run {
            val parseDataWithJson = ParseDataWithJson()
            val standings = parseDataWithJson.parseJson(
                jsonArray.toString(),
                TypeModel.STANDING
            ) as MutableList<Standing>?
            StandingGroup(standings = standings)
        }

    @Throws(Exception::class)
    fun parseJsonToStanding(jsonObject: JSONObject?): Standing? =
        jsonObject?.run {
            Standing(
                rank = getInt(StandingEntry.RANK),
                team = parseJsonToStandingTeam(getJSONObject(StandingEntry.TEAM)),
                points = getInt(StandingEntry.POINTS),
                goalsDiff = getInt(StandingEntry.GOALS_DIFF),
                group = getString(StandingEntry.GROUP),
                all = parseJsonToStandingAll(getJSONObject(StandingEntry.ALL))
            )
        }

    @Throws(Exception::class)
    private fun parseJsonToStandingTeam(jsonObject: JSONObject?): Team? =
        jsonObject?.run {
            Team(
                id = getInt(TeamEntry.ID),
                name = getString(TeamEntry.NAME),
                logo = getString(TeamEntry.LOGO)
            )
        }

    @Throws(Exception::class)
    private fun parseJsonToStandingAll(jsonObject: JSONObject?): All? =
        jsonObject?.run {
            All(
                played = getInt(AllEntry.PLAYED),
                win = getInt(AllEntry.WIN),
                draw = getInt(AllEntry.DRAW),
                lose = getInt(AllEntry.LOSE)
            )
        }

    @Throws(Exception::class)
    fun parseJsonToTopScorer(jsonObject: JSONObject?): TopScorer? =
        jsonObject?.run {
            TopScorer(
                player = parseJsonToPlayer(getJSONObject(TopScorerEntry.PLAYER)),
                statistic = parseJsonToStatistic(
                    getJSONArray(TopScorerEntry.STATISTIC).getJSONObject(
                        0
                    )
                )
            )
        }

    @Throws(Exception::class)
    fun parseJsonToPlayer(jsonObject: JSONObject?): Player? =
        jsonObject?.run {
            Player(
                id = getInt(PLayerEntry.ID),
                name = getString(PLayerEntry.NAME),
                age = getInt(PLayerEntry.AGE),
                nationality = getString(PLayerEntry.NATIONALITY),
                height = getString(PLayerEntry.HEIGHT),
                weight = getString(PLayerEntry.WEIGHT),
                photo = getString(PLayerEntry.PHOTO)
            )
        }

    @Throws(Exception::class)
    private fun parseJsonToStatistic(jsonObject: JSONObject?): Statistic? =
        jsonObject?.run {
            Statistic(
                team = parseJsonToTeam(getJSONObject(StatisticEntry.TEAM)),
                goals = getJSONObject(StatisticEntry.GOALS).getInt(StatisticEntry.TOTAL)
            )
        }

    fun parseJsonSeason(jsonArray: JSONArray?): Season? =
        jsonArray?.run {
            Log.e("xxx", jsonArray.toString()   )
            Season(season = jsonArray as MutableList<String>)
        }
}
