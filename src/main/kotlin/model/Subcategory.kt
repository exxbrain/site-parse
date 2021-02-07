package model

class Subcategories {
    companion object {
        fun subcategories(vararg name: String): List<Subcategory> {
            return name
                .mapIndexed{ index, it -> Subcategory(name=it, index=index) }
        }
    }
}

class Subcategory(val name: String, val code: String = name.urlize(), val index: Int)