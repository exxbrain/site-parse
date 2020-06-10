package model

import files.RemoteFile

class Product(
    val productId: Long,
    val name: String = "",
    val model: String = "",
    val manufacturer: String = "",
    val images: List<RemoteFile> = listOf(),
    var imageName: String = "",
    val category: String = "",
    val categoryId: Long,
    val price: Price = Price.ZERO,
    val sku: String = "",
    val weight: Double = .0,
    val length: Double = .0,
    val width: Double = .0,
    val height: Double = .0,
    val description: String = "",
    var seo: String,
    val additionalImages: List<String> = listOf(),
    val options: LinkedHashMap<String, List<String>> = linkedMapOf(),
    val imageFolder: String
) {
    var defaultOptionValues: Map<String, String> = options.map { Pair(it.key, it.value[0]) }.toMap()
}