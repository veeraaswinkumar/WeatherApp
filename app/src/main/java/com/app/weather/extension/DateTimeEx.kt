package com.app.weather.extension

import java.text.SimpleDateFormat
import java.util.*

var dateFormat = "MMM d, yyyy";
var timeFormat = "h:mm a";
var day = "EEE"


fun String.getDateFromTimeStamp() : String {
    val dateFormat = SimpleDateFormat(dateFormat,Locale.US)
    val cal = Calendar.getInstance()
    cal.timeInMillis = this.toLong() * 1000L
    return dateFormat.format(cal.time)
}

fun String.getTimeFromTimeStamp() : String {
    val sdf = SimpleDateFormat(timeFormat, Locale.US)
    val cal = Calendar.getInstance()
    cal.timeInMillis = this.toLong() * 1000L
    return sdf.format(cal.time)
}
fun String.getDayFromTimeStamp() : String {
    val sdf = SimpleDateFormat(day, Locale.US)
    val cal = Calendar.getInstance()
    cal.timeInMillis = this.toLong() * 1000L
    return sdf.format(cal.time)
}
