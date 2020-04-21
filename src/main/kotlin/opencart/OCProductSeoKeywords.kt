package opencart

import excel.DataSource
import model.Product
import java.util.*

class OCProductSeoKeywords(private val product: Product) : DataSource {
    override val data: List<LinkedHashMap<String, Any>> get() {
        return listOf(linkedMapOf(
            "product_id" to product.productId,
            "store_id" to 0,
            "keyword(ru-ru)" to product.seo
        ))
    }

}
