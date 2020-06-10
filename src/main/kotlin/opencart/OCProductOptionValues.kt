package opencart

import excel.DataSource
import model.Product
import java.util.LinkedHashMap

class OCProductOptionValues(private val product: Product): DataSource {
    override val data: List<LinkedHashMap<String, Any>> get() {
        return product.options.flatMap {
            val key = it.key
            return it.value.map {
                linkedMapOf(
                    "product_id" to product.productId,
                    "option" to key,
                    "option_value" to it,
                    "quantity" to 2,
                    "subtract" to "false",
                    "price" to 0,
                    "price_prefix" to "+",
                    "points" to 0,
                    "points_prefix" to "+",
                    "weight" to 0,
                    "weight_prefix" to "+"
                )
            }
        }
    }

}
