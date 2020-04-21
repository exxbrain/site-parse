package opencart

import excel.DataSource
import model.Product
import java.util.LinkedHashMap

class OCProductOptionValues(private val product: Product): DataSource {
    override val data: List<LinkedHashMap<String, Any>> get() {
        return product.options.map {
            linkedMapOf(
                "product_id" to product.productId,
                "option" to it.key,
                "option_value" to it.value,
                "quantity" to 2,
                "subtract" to "false",
                "price" to 0.0,
                "price_prefix" to "+",
                "points" to "0",
                "weight" to 0.0,
                "points_prefix" to "+",
                "weight_prefix" to "+"
            )
        }
    }

}
