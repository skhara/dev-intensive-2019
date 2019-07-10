package ru.skillbranch.devintensive.extensions

fun Long.pluralsOf(timeUnit : TimeUnits) : String {

    val words = when(timeUnit) {
        TimeUnits.SECOND -> Triple("секунду", "секунды", "секунд")
        TimeUnits.MINUTE -> Triple("минуту", "минуты", "минут")
        TimeUnits.HOUR -> Triple("час", "часа", "часов")
        TimeUnits.DAY -> Triple("день", "дня", "дней")
    }

    val hundreds = this.div(100)
    val lastTwoDigits = this - (hundreds * 100)

    return when(if (lastTwoDigits < 15) lastTwoDigits else lastTwoDigits % 10) {
        1L -> words.first
        in 2..4 -> "$this ${words.second}"
        else -> "$this ${words.third}"
    }
}