package catalog

import model.ProductCollection
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.FileOutputStream

class ExcelCollection(private val collection: ProductCollection) {
    fun save(dirname: String, imagesDirname: String) {
        collection.gallery.forEach { x -> x.download() }}
        collection.products.forEach { x -> x.images.forEach { image -> image.download(imagesDirname) }}
        val wb = HSSFWorkbook()
        ExcelProductsSheet(collection.products).addTo(wb)
        FileOutputStream("./${dirname}/import.xls").use {
            wb.write(it)
        }
    }
}