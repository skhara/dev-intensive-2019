package ru.skillbranch.devintensive.extensions

fun Long.pluralsOf(timeUnit : TimeUnits) : String {

    val words = when(timeUnit) {
        TimeUnits.SECOND -> Triple("секунду", "секунды", "секунд")
        TimeUnits.MINUTE -> Triple("минуту", "минуты", "минут")
        TimeUnits.HOUR -> Triple("час", "часа", "часов")
        TimeUnits.DAY -> Triple("день", "дня", "дней")

        else -> Triple("единицу", "единицы", "единиц")
    }

    return when(if (this < 15) this else this % 10) {
        1L -> words.first
        in 2..4 -> "$this ${words.second}"
        else -> "$this ${words.third}"
    }
}