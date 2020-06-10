package model

import files.Files
import kotlinx.coroutines.runBlocking
import opencart.OCExcelWb
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset

class Page (
    private val url: String,
    private val code: String,
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

        val options = ChromeOptions()
        options.addArguments("--whitelisted-ips=''", "--no-sandbox", "--verbose")
        val driver = ChromeDriver(options)
        driver.get(url)
        beforeDownload?.let { it(driver) }
        val pageSource = driver.pageSource
        FileOutputStream(File(filePath)).use {
            it.write(pageSource.toByteArray(Charset.forName("WINDOWS-1251")))
        }
        driver.close()
    }

    fun save(ignoreImages: Boolean = false) {
        val folder = File(targetFolder)
        if (folder.exists() && !ignoreImages) {
            folder.deleteRecursively()
        }
        if (header != null && !ignoreImages) {
            runBlocking {
                Files(header.getImages(filePath)).download("$targetFolder/gallery")
            }
        }

        OCExcelWb(products.getProducts(filePath)).save("$targetFolder/products.xls")
        runBlocking {
            if(!ignoreImages) {
                Files(products.getImages(filePath)).download("$targetFolder/$code")
            }
        }
    }
}