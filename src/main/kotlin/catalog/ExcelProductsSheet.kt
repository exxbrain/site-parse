package catalog

import model.Product
import excel.Sheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

internal class ExcelProductsSheet(private val products: List<Product>) {
    fun addTo(workbook: HSSFWorkbook) {
        val sheet = Sheet(workbook.createSheet("Products"))
        for (product in products) {
            val excelProduct = ExcelProduct(product)
            excelProduct.addRow(sheet)
        }
        sheet.createHeaderRow()
    }
}