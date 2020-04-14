package opencart

import excel.WithData
import model.Collection
import model.RemoteFile
import model.Product
import java.util.*

class OpenCartCollection(collection: Collection) {

    private var products: List<Product> = collection.products

    fun getData(imgFolder: String): LinkedHashMap<String, List<WithData>> {
        return linkedMapOf (
            "Products" to products.map { OpenCartProduct(it, imgFolder) },
            "ProductSEOKeywords" to products.map { OpenCartProductSeoKeywords(it) }
        )
    }

    fun getImages(): List<RemoteFile> {
        return products.flatMap { it.images }
    }
}