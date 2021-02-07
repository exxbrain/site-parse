package model

data class Product internal constructor(
    val productId: Long,
    val name: String = "",
    val model: String = "",
    val image: Image,
    val subcategory: String = "",
    val price: Price = Price.ZERO,
    val sku: String = "",
    val weight: Double = .0,
    val length: Double = .0,
    val width: Double = .0,
    val height: Double = .0,
    val description: String = "",
    val seo: String,
    val collection: ProductCollection
) {
    val mainImage = if (collection.imageFolders.isNotEmpty()) "${collection.imageFolders[0]}/${image.fileName}" else null
    val additionalImages = if (collection.imageFolders.size > 1)
        collection.imageFolders.subList(1, collection.imageFolders.size).map { "$it/${image.fileName}" }
        else emptyList()
    val options = linkedMapOf("Цвет" to collection.subcategories.map { it.name })
    val defaultOptionValues = options.map { Pair(it.key, it.value[0]) }.toMap()
    val categoryId = collection.categoryId
    val manufacturer = collection.manufacturer.name
}