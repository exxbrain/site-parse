package model

data class Image (
    val url: String,
    var name: String
) {
    val fileName get() = "$name.${url.substringAfterLast('.', "")}"
}

