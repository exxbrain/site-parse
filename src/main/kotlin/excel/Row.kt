package excel

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.ss.usermodel.CellType
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class Row(
    private val row: HSSFRow,
    private val columnNames: MutableList<String>
) {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

    fun createCell(name: String, value: Any): Row {
        if (!columnNames.contains(name)) {
            columnNames.add(name)
        }
        val index = columnNames.indexOf(name)
        createCell(index, value)
        return this
    }

    fun createCell(index: Int, value: Any): Row {
        when(value) {
            is String -> row.createCell(index, CellType.STRING).setCellValue(value)
            is Int, Long, Float -> row.createCell(index, CellType.NUMERIC).setCellValue(value.toString())
            is Date -> row.createCell(index, CellType.STRING).setCellValue(dateFormatter.format(value))
            is Boolean -> row.createCell(index, CellType.STRING).setCellValue(if (value) "true" else "false")
            is BigDecimal -> row.createCell(index, CellType.NUMERIC).setCellValue(value.toString())
            is List<*> -> row.createCell(index, CellType.STRING).setCellValue(value.joinToString {x -> x.toString()})
            else -> row.createCell(index, CellType.STRING).setCellValue(value.toString())
        }
        return this
    }
}