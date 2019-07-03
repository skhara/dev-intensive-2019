package ru.skillbranch.devintensive.utils

object Utils {

    private val dictionary : List<Pair<String, String>> = listOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )

    fun parseFullName(fullName : String?) : Pair<String?, String?> {

        val parts : List<String>? = fullName?.split("\\s+".toRegex())
        val firstName : String? = parts?.getOrNull(0)
        val lastName : String? = parts?.getOrNull(1)

        return firstName?.emptyToNull() to lastName?.emptyToNull();
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val result = letter(firstName) + letter(lastName)
        return result.emptyToNull()
    }

    private fun letter(word : String?) : String {
        val trimmed = word?.trim()
        val index = if (trimmed != "") 1 else 0

        return trimmed?.substring(0, index)?.toUpperCase().orEmpty()
    }

    fun transliteration(payload : String, divider : String = " ") : String {
        val (first, last) = parseFullName(payload)

        return translit(first!!.toLowerCase()) + divider + translit(last!!.toLowerCase())
    }

    private fun translit(word : String) : String {
        var result : String = word
        for (element in dictionary) {
            result = result.replace(element.first, element.second)
        }

        return result.capitalize()
    }
}

fun String.emptyToNull() : String? {
    return if (isEmpty()) null else this
}