package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val difference = abs(date.time - this.time)
    val b: Int = (difference / DAY).toInt()
        println(difference)

    return when (difference) {
        in 0..SECOND -> "только что"
        in SECOND..SECOND * 45 -> "несколько секунд назад"
        in SECOND * 45..SECOND * 75 -> "минуту назад"
        in SECOND * 75..MINUTE * 45 -> "N минут назад"
        in MINUTE * 45..MINUTE * 75 -> "час назад"
        in MINUTE * 75..HOUR * 22 -> "N часов назад"
        in HOUR * 22..HOUR * 26 -> "день назад"
        in HOUR * 26..DAY * 360 -> "N дней назад"
        else -> "более года назад"

//        in MINUTE..HOUR -> "${Utils.plural(arrayOf("минута", "минуты", "минут"), (difference / MINUTE).toInt())} назад"
//        in HOUR..DAY -> "${Utils.plural(arrayOf("час", "часа", "часов"), (difference / HOUR).toInt())} назад"
//        else -> {
//            if (b > 365) "более года назад" else "${Utils.plural(arrayOf("день", "дня", "дней"), b)} назад"
//        }
    }
}
