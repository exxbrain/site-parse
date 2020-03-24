package catalog

import model.Product
import excel.Sheet
import java.util.*
import kotlin.collections.HashMap

internal class ExcelProduct(private val product: Product) {
    fun getData(): List<Pair<String, Any>> {
        return arrayListOf(
            Pair("productId", product.productId),
            Pair("name(ru-ru)", product.name),
            Pair("categories", product.categories),
            Pair("sku", product.sku),
            Pair("upc", ""),
            Pair("ean", ""),
            Pair("jan", ""),
            Pair("isbn", ""),
            Pair("mpn", ""),
            Pair("location", ""),
            Pair("quantity", 2),
            Pair("model", product.model),
            Pair("manufacturer", product.manufacturer),
            Pair("image_name", if (product.images.any()) product.images[0] else ""),
            Pair("shipping", true),
            Pair("price", 0),
            Pair("points", 0),
            Pair("date_added", Date()),
            Pair("date_modified", Date()),
            Pair("date_available", Date()),
            Pair("weight", product.weight),
            Pair("weight_unit", "kg"),
            Pair("length", product.length),
            Pair("width", product.width),
            Pair("height", product.height),
            Pair("length_unit", "cm"),
            Pair("status", false),
            Pair("tax_class_id", 0),
            Pair("description(ru-ru)", product.description),
            Pair("meta_title(ru-ru)", product.metaTitle),
            Pair("meta_description(ru-ru)", product.metaDescription),
            Pair("meta_keywords(ru-ru)", product.metaKeywords),
            Pair("stock_status_id", 6),
            Pair("store_ids", "0"),
            Pair("layout", "0:"),
            Pair("related_ids", ""),
            Pair("tags(ru-ru)", product.tags),
            Pair("sort_order", 1),
            Pair("subtract", true),
            Pair("minimum", 1)
        )
    }
}