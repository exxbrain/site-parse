package opencart

import excel.DataSource
import excel.ExcelWb
import model.Product

class OCExcelWb(private val products: List<Product>) {

    private fun getData(): LinkedHashMap<String, List<DataSource>> {
        return linkedMapOf (
            "Products" to products.map { OCProducts(it) },
            "ProductSEOKeywords" to products.map { OCProductSeoKeywords(it) },
            "AdditionalImages" to products.map { OCAdditionalImages(it) },
            "ProductOptions" to products.map { OCProductOptions(it) },
            "ProductOptionValues" to products.map { OCProductOptionValues(it) }
        )
    }
    fun save(filePath: String) {
        ExcelWb(getData()).save(filePath)
    }
}