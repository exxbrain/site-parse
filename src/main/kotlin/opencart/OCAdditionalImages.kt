package opencart

import excel.DataSource
import model.Product
import java.util.LinkedHashMap

class OCAdditionalImages(private val product: Product) : DataSource {
    override fun getData(): List<LinkedHashMap<String, Any>> {
        return product.additionalImages.mapIndexed { index, image ->
            linkedMapOf(
                "product_id" to product.productId,
                "image" to image,
                "sort_order" to index
            )
        }
    }

}
