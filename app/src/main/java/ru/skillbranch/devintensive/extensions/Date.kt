package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val format = SimpleDateFormat(pattern, Locale("ru"))
    return format.format(this)
}

const val SECONDS = 1000L
const val MINUTES = SECONDS * 60L
const val HOURS = MINUTES * 60L
const val DAYS = HOURS * 24L

fun Date.add(value : Int, timeUnits : TimeUnits = TimeUnits.SECOND) : Date {

    var time = this.time

    when(timeUnits) {
        TimeUnits.SECOND -> time += value * SECONDS
        TimeUnits.MINUTE -> time += value * MINUTES
        TimeUnits.HOUR -> time += value * HOURS
        TimeUnits.DAY -> time += value * DAYS
    }

    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

private val NOW = LongRange(0, 1 * SECONDS)
private val N_SECONDS = LongRange(1 * SECONDS, 45 * SECONDS)
private val MINUTE = LongRange(45 * SECONDS, 75 * SECONDS)
private val N_MINUTES = LongRange(75 * SECONDS, 45 * MINUTES)
private val HOUR = LongRange(45 * MINUTES, 75 * MINUTES)
private val N_HOURS = LongRange(75 * MINUTES, 22 * HOURS)
private val DAY = LongRange(22 * HOURS, 26 * HOURS)
private val N_DAY = LongRange(26 * HOURS, 360 * DAYS)

fun Date.humanizeDiff(date : Date = Date()) : String {
    val diff = date.time - this.time
    val prefix = if (diff < 0) "через" else ""
    val postfix = if (diff > 0) "назад" else ""

    val diffAbs = diff.absoluteValue
    val diffMessage = when(diffAbs) {
        in NOW -> "только что"
        in N_SECONDS -> "$prefix несколько секунд $postfix"
        in MINUTE -> "$prefix минуту $postfix"
        in N_MINUTES -> "$prefix ${ (diffAbs / MINUTES).pluralsOf(TimeUnits.MINUTE)} $postfix"
        in HOUR -> "$prefix час $postfix"
        in N_HOURS -> "$prefix ${ (diffAbs / HOURS).pluralsOf(TimeUnits.HOUR) } $postfix"
        in DAY -> "$prefix день $postfix"
        in N_DAY -> "$prefix ${( diffAbs / DAYS ).pluralsOf(TimeUnits.DAY)} $postfix"

        else ->  if (diff > 0) "более года назад" else "более чем через год"
    }

    return diffMessage.trim()
}