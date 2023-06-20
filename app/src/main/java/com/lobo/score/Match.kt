package com.lobo.score

import com.google.gson.annotations.SerializedName
data class Match(
    @SerializedName("sport_name") val sportName: String,
    val country: String,
    @SerializedName("tournament_name") val tournamentName: String,
    @SerializedName("team_1") val team1: String,
    @SerializedName("team_2") val team2: String,
    @SerializedName("match_date") val matchDate: String,
    val total: Total?,
    val fora: Fora?
)

data class Total(
    @SerializedName("@freetext") val freeText: String,
    @SerializedName("@value") val value: String,
    @SerializedName("@name1") val name1: String,
    @SerializedName("@odd1") val odd1: String,
    @SerializedName("@name2") val name2: String,
    @SerializedName("@odd2") val odd2: String
)

data class Fora(
    @SerializedName("@freetext") val freeText: String,
    @SerializedName("@value") val value: String,
    @SerializedName("@name1") val name1: String,
    @SerializedName("@odd1") val odd1: String,
    @SerializedName("@name2") val name2: String,
    @SerializedName("@odd2") val odd2: String
)