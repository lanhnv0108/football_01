package com.sun.uefascore.data.model

data class Team(
    val id: Int?,
    val name: String?,
    val logo: String?,
    val country: String?,
    val founded: Int?
)

object TeamEntry {
    const val TEAM = "team"
    const val COUNTRY = "country"
    const val FOUNDED = "founded"
    const val ID = "id"
    const val NAME = "name"
    const val LOGO = "logo"
}
