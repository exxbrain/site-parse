package excel

import org.apache.poi.hssf.usermodel.HSSFSheet

class Sheet(private val sheet: HSSFSheet) {
    private var currentIndex = 1;
    private val columnNames = mutableListOf<String>()

    fun createRow(): Row {
        return Row(sheet.createRow(currentIndex++), columnNames)
    }

    fun createHeaderRow() {
        val row = Row(sheet.createRow(0), columnNames)
        columnNames.forEachIndexed { index, s ->
            row.createCell(index, s)
        }
    }
}