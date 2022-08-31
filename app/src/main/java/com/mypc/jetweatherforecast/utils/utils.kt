package com.mypc.jetweatherforecast.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timestamp: Int):String {
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}


fun getDayOfWeek(timestamp: Int):String {
    val sdf = SimpleDateFormat("EEE")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatHour(timeStamp:Int):String{
    val tf = SimpleDateFormat("HH:MM aa")
    val time = java.util.Date(timeStamp.toLong() * 1000)
    return tf.format(time)

}

fun formatDecimals(item:Double):String {
    return "%.0f".format(item)
}