package excel

import excel.WithData
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellType
import java.io.FileOutputStream
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.LinkedHashMap

class ExcelWb(private val data: LinkedHashMap<String, List<WithData>>) {

    private val wb: HSSFWorkbook = HSSFWorkbook()
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

    fun save(fileName: String) {
        for (sheetData in data) {
            addSheet(sheetData.key, sheetData.value)
        }
        FileOutputStream(fileName).use {
            wb.write(it)
        }
    }

    private fun addSheet(name: String, data: List<WithData>) {
        val sheet = wb.createSheet(name)
        var rowIndex = 1;
        val columnNames = mutableListOf<String>()
        val rows = data.map { it.getData() }
        rows.forEach { rowData -> rowData.forEach { columnData ->
            if (!columnNames.contains(columnData.key)) {
                columnNames.add(columnData.key)
            }
        }}
        for (rowData in rows) {
            val row = sheet.createRow(rowIndex++)
            for (columnData in rowData) {
                createCell(row, columnNames.indexOf(columnData.key), columnData.value)
            }
        }
        val headerRow = sheet.createRow(0)
        columnNames.forEachIndexed { index, value -> createCell(headerRow, index, value)}
    }

    private fun createCell(row: HSSFRow, index: Int, value: Any) {
        when(value) {
            is String -> row.createCell(index, CellType.STRING).setCellValue(value)
            is Int, Long, Float -> row.createCell(index, CellType.NUMERIC).setCellValue(value.toString())
            is Date -> row.createCell(index, CellType.STRING).setCellValue(dateFormatter.format(value))
            is Boolean -> row.createCell(index, CellType.STRING).setCellValue(if (value) "true" else "false")
            is BigDecimal -> row.createCell(index, CellType.NUMERIC).setCellValue(value.toString())
            is List<*> -> row.createCell(index, CellType.STRING).setCellValue(value.joinToString { x -> x.toString()})
            else -> row.createCell(index, CellType.STRING).setCellValue(value.toString())
        }
    }
}