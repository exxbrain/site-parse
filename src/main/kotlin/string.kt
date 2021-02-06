package model

import com.ibm.icu.text.Transliterator

private val toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN")

fun String.urlize(): String {
    return toLatinTrans.transliterate(this)
        .toLowerCase()
        .replace(Regex("[^a-z0-9-]"), "_")
}

fun String.find(regex: Regex): String? {
    return regex.find(this)?.groupValues?.last()?.trim()
}