package model

data class Product(
    val productId: Long,
    val name: String,
    val categories: List<Long>,
    val sku: String,
    val model: String,
    val manufacturer: String,
    val images: List<HttpFile>,
    val weight: Float,
    val length: Float,
    val width: Float,
    val height: Float,
    val description: String,
    val metaTitle: String,
    val metaDescription: String,
    val metaKeywords: String,
    val tags: String
    )
