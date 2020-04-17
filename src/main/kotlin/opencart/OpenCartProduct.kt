package opencart

import excel.WithData
import model.Product
import java.util.*

internal data class OpenCartProduct(private val product: Product, private val imgFolder: String) :
    WithData {
    override fun getData(): LinkedHashMap<String, Any> {
        return linkedMapOf(
            "product_id" to product.productId,
            "name(ru-ru)" to "${product.name} ${product.model}",
            "categories" to product.categoryId,
            "sku" to product.sku,
            "upc" to "",
            "ean" to "",
            "jan" to "",
            "isbn" to "",
            "mpn" to "",
            "location" to "",
            "quantity" to 2,
            "model" to product.model,
            "manufacturer" to product.manufacturer,
            "image_name" to if (product.images.any()) "catalog/$imgFolder/${product.images[0].fileName}" else "",
            "shipping" to true,
            "price" to 0,
            "points" to 0,
            "date_added" to Date(),
            "date_modified" to Date(),
            "date_available" to Date(),
            "weight" to product.weight,
            "weight_unit" to "kg",
            "length" to product.length,
            "width" to product.width,
            "height" to product.height,
            "length_unit" to "cm",
            "status" to true,
            "tax_class_id" to 0,
            "description(ru-ru)" to product.description,
            "meta_title(ru-ru)" to "${product.name} ${product.model} купить с доставкой в магазине Ральф-Интерьер",
            "meta_description(ru-ru)" to "${product.name} ${product.model} ${product.description} ${product.category} купить с доставкой в магазине Ральф-Интерьер",
            "meta_keywords(ru-ru)" to "${product.category},${product.name},${product.model}",
            "stock_status_id" to 6,
            "store_ids" to "0",
            "layout" to "0:",
            "related_ids" to "",
            "tags(ru-ru)" to "${product.category},${product.name}",
            "sort_order" to 1,
            "subtract" to true,
            "minimum" to 1
        )
    }
}