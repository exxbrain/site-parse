package model

class Colors {
    companion object {
        fun colors(vararg name: String): List<Color> {
            return name
                .mapIndexed{ index, it -> Color(name=it, index=index) }
        }
    }
}

class Color(val name: String, val code: String = name.urlize(), val index: Int)