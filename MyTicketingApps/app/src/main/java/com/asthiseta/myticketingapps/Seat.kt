package com.asthiseta.myticketingapps

data class Seat(
    val id: Int,
    var x: Float? = 0F,
    var y: Float? = 0F,
    var name : String,
    var isBooked : Boolean
)
