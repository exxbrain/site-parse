package opencart

import excel.DataSource
import model.Product
import java.util.LinkedHashMap

class OCProductOptions(private val product: Product) : DataSource {
    override val data: List<LinkedHashMap<String, Any>> get() {
        return product.options.keys.map {
            linkedMapOf(
                "product_id" to product.productId,
                "option" to it,
                "default_option_value" to product.defaultOptionValues.getOrDefault(it, ""),
                "required" to "true"
            )
        }
    }
}
