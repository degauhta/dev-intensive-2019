package ru.skillbranch.devintensive.extensions

fun String.truncate(trSize: Int = 17): String {
    return if (this.length > trSize) {
        var result = this.substring(0, trSize)
        if (result.last().isWhitespace()) result = result.dropLast(1)
        if (result.last().isWhitespace()) {
            result.trim()
        } else {
            result.plus("...")
        }
    } else {
        this.trim()
    }
}

fun String.stripHtml(): String {
    val indexOfOpen = this.indexOf(">") + 1
    val indexOfClose = this.indexOf("<", indexOfOpen)

    return if (indexOfClose > indexOfOpen)
        this.substring(indexOfOpen, indexOfClose).trim().replace(
            " +".toRegex(), " "
        ) else ""
}