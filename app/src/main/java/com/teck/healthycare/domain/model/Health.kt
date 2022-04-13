package com.teck.healthycare.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Health(
    val time: String? = "",
    val pulse: Int? = 0,
    var heartBreak: Int? = 0,
    var heartBeat: Int? = 0
) {
}