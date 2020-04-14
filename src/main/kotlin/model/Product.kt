package model

import java.math.BigDecimal

class Product(
    val productId: Long,
    val name: String = "",
    val model: String = "",
    val manufacturer: String = "",
    val images: List<RemoteFile> = listOf(),
    val category: String = "",
    val price: BigDecimal = 0.toBigDecimal(),
    val sku: String = "",
    val weight: Double = .0,
    val length: Double = .0,
    val width: Double = .0,
    val height: Double = .0,
    val description: String = ""
)