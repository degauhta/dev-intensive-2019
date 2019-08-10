package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

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
    val b: Int = ((date.time - this.time) / DAY).toInt()
    return when (date.time - this.time) {
        in 0..MINUTE -> "только что"
        in MINUTE..HOUR -> "${Utils.plural(arrayOf("минута", "минуты", "минут"), ((date.time - this.time) / MINUTE).toInt())} назад"
        in HOUR..DAY -> "${Utils.plural(arrayOf("час", "часа", "часов"), ((date.time - this.time) / HOUR).toInt())} назад"
        else -> {
            if (b > 365) "более года назад" else "${Utils.plural(arrayOf("день", "дня", "дней"), b)} назад"
        }
    }
}
