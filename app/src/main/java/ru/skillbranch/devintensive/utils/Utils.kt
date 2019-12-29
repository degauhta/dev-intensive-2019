package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = if (fullName?.replace(" ", "")?.length != 0)
            fullName?.trim()?.split(" +".toRegex()) else null
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val sb = StringBuilder()
        for (c in payload) {
            when (c.toLowerCase()) {
                in "а" -> sb.append(if (c.isLowerCase()) "a" else "A")
                in "б" -> sb.append(if (c.isLowerCase()) "b" else "B")
                in "в" -> sb.append(if (c.isLowerCase()) "v" else "V")
                in "г" -> sb.append(if (c.isLowerCase()) "g" else "G")
                in "д" -> sb.append(if (c.isLowerCase()) "d" else "D")
                in "е" -> sb.append(if (c.isLowerCase()) "e" else "E")
                in "ё" -> sb.append(if (c.isLowerCase()) "e" else "E")
                in "ж" -> sb.append(if (c.isLowerCase()) "zh" else "Zh")
                in "з" -> sb.append(if (c.isLowerCase()) "z" else "Z")
                in "и" -> sb.append(if (c.isLowerCase()) "i" else "I")
                in "й" -> sb.append(if (c.isLowerCase()) "i" else "I")
                in "к" -> sb.append(if (c.isLowerCase()) "k" else "K")
                in "л" -> sb.append(if (c.isLowerCase()) "l" else "L")
                in "м" -> sb.append(if (c.isLowerCase()) "m" else "M")
                in "н" -> sb.append(if (c.isLowerCase()) "n" else "N")
                in "о" -> sb.append(if (c.isLowerCase()) "o" else "O")
                in "п" -> sb.append(if (c.isLowerCase()) "p" else "P")
                in "р" -> sb.append(if (c.isLowerCase()) "r" else "R")
                in "с" -> sb.append(if (c.isLowerCase()) "s" else "S")
                in "т" -> sb.append(if (c.isLowerCase()) "t" else "T")
                in "у" -> sb.append(if (c.isLowerCase()) "u" else "U")
                in "ф" -> sb.append(if (c.isLowerCase()) "f" else "F")
                in "х" -> sb.append(if (c.isLowerCase()) "h" else "H")
                in "ц" -> sb.append(if (c.isLowerCase()) "c" else "C")
                in "ч" -> sb.append(if (c.isLowerCase()) "ch" else "Ch")
                in "ш" -> sb.append(if (c.isLowerCase()) "sh" else "Sh")
                in "щ" -> sb.append(if (c.isLowerCase()) "sh'" else "Sh'")
                in "ъ" -> sb.append("")
                in "ы" -> sb.append(if (c.isLowerCase()) "i" else "I")
                in "ь" -> sb.append("")
                in "э" -> sb.append(if (c.isLowerCase()) "e" else "E")
                in "ю" -> sb.append(if (c.isLowerCase()) "yu" else "Yu")
                in "я" -> sb.append(if (c.isLowerCase()) "ya" else "Ya")
                in " " -> sb.append(divider)
                else -> sb.append(c)
            }
        }
        return sb.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val sb: StringBuilder = StringBuilder()
        if (firstName == null && lastName == null ||
            firstName?.replace(" ", "")?.length == 0 && lastName?.replace(" ", "")?.length == 0
        ) {
            return null
        }
        if (firstName != null && firstName.replace(" ", "").isNotEmpty()) {
            sb.append(firstName[0].toUpperCase())
        }
        if (lastName != null && lastName.replace(" ", "").isNotEmpty()) {
            sb.append(lastName[0].toUpperCase())
        }
        return sb.toString()
    }

    fun plural(word: Array<String>, n: Int): String =
        when (if (n % 10 == 1 && n % 100 != 11) 0 else if (n % 10 in 2..4 && (n % 100 < 10 || n % 100 >= 20)) 1 else 2) {
            0 -> "$n ${word[0]}"
            1 -> "$n ${word[1]}"
            else -> "$n ${word[2]}"
        }
}