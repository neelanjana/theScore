package com.thescore.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamModel(
    var id: Int? = null,
    var wins: Int? = null,
    var losses: Int? = null,
    var full_name: String? = null,
    var players: List<Players?>? = null
) : Parcelable

@Parcelize
data class Players(
    var id: Int,
    var first_name: String? = null,
    var last_name: String? = null,
    var position: String? = null,
    var number: Int? = null
) : Parcelable