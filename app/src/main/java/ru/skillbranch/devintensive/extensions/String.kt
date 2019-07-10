package ru.skillbranch.devintensive.extensions

fun String.truncate(num: Int = 16): String {
    val trimmed = this.trim()
    return if (num > trimmed.length - 1)
        trimmed
    else
        trimmed.subSequence(0, num).trimEnd().toString() + "..."
}

fun String.stripHtml(): String {
    return this
        .replace("<[a-zA-Z/][^>]*>".toRegex(), "")
        .replace("&\\d+;|&#\\d+;|&\\w+;".toRegex(), "")
        .replace("\\s{2,}".toRegex(), " ")
}