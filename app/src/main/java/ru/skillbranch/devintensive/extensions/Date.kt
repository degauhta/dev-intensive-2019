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

fun TimeUnits.plural(counter: Int): String {
    return when {
        this == TimeUnits.SECOND -> {
            Utils.plural(arrayOf("секунду", "секунды", "секунд"), counter)
        }
        this == TimeUnits.MINUTE -> {
            Utils.plural(arrayOf("минута", "минуты", "минут"), counter)
        }
        this == TimeUnits.HOUR -> {
            Utils.plural(arrayOf("час", "часа", "часов"), counter)
        }
        this == TimeUnits.DAY -> {
            Utils.plural(arrayOf("день", "дня", "дней"), counter)
        }
        else -> ""
    }
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val difference = (this.time - date.time) / 1000

    val s = 1L
    val m = 60 * s
    val h = 60 * m
    val d = 24 * h

    return when (difference) {
        in 0..s -> "только что"

        in s..(s * 45) -> "через несколько секунд"
        in -s downTo (-s * 45) -> "несколько секунд назад"

        in (s * 45)..(s * 75) -> "через минуту"
        in (-s * 45) downTo (-s * 75) -> "минуту назад"

        in s * 75..m * 45 -> "через ${Utils.plural(
            arrayOf("минута", "минуты", "минут"),
            (difference / m).toInt()
        )}"
        in (-s * 75) downTo (-m * 45) -> "${Utils.plural(
            arrayOf("минута", "минуты", "минут"),
            -(difference / m).toInt()
        )} назад"

        in m * 45..m * 75 -> "через час"
        in (-m * 45) downTo (-m * 75) -> "час назад"

        in (m * 75)..(h * 22) -> "через ${Utils.plural(
            arrayOf("час", "часа", "часов"),
            (difference / h).toInt()
        )}"
        in (-m * 75) downTo (-h * 22) -> "${Utils.plural(
            arrayOf("час", "часа", "часов"),
            -(difference / h).toInt()
        )} назад"

        in h * 22..h * 26 -> "через день"
        in (-h * 22) downTo (-h * 26) -> "день назад"

        in h * 26..d * 360 -> "через ${Utils.plural(
            arrayOf("день", "дня", "дней"),
            (difference / d).toInt()
        )}"

        in (-h * 26) downTo (-d * 360) -> "${Utils.plural(
            arrayOf("день", "дня", "дней"),
            -(difference / d).toInt()
        )} назад"

        else -> if (difference > 0) "более чем через год" else "более года назад"
    }
}
