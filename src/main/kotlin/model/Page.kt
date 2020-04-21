package model

import files.Files
import kotlinx.coroutines.runBlocking
import opencart.OCExcelWb
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset

class Page (
    private val url: String,
    private val filePath: String,
    private val targetFolder: String,
    private val products: Products,
    private val header: Header? = null,
    private val beforeDownload: ((driver: ChromeDriver) -> Unit)? = null
) {

    fun download() {
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
            throw Exception("AAA")
        }
        file.createNewFile()

        val driver = ChromeDriver()
        driver.get(url)
        beforeDownload?.let { it(driver) }
        val pageSource = driver.pageSource
        FileOutputStream(File(filePath)).use {
            it.write(pageSource.toByteArray(Charset.forName("WINDOWS-1251")))
        }
        driver.close()
    }

    fun save() {
        val folder = File(targetFolder)
        if (folder.exists()) {
            folder.deleteRecursively()
        }
        if (header != null) {
            runBlocking {
                Files(header).download("$targetFolder/gallery")
            }
        }

        OCExcelWb(products.getProducts(filePath)).save("$targetFolder/products.xls")
        runBlocking {
            Files(products.getImages(filePath)).download("$targetFolder/images")
        }
    }
}