package opencart

import files.RemoteFile
import model.Product
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.properties.Delegates

internal class OCExcelWbTest {

    @Test
    fun save() {
        OCExcelWb(listOf(Product(
            productId = 1,
            categoryId = 3,
            images = listOf(RemoteFile(
                "http://aa.e", "test"
            )),
            imageFolder = "test",
            seo = "a"
        ))).save("src/test/resources/test.xls")
    }
}