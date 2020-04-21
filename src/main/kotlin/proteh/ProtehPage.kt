package proteh

import files.Files
import files.TextFile
import kotlinx.coroutines.runBlocking
import opencart.OCExcelWb
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File

class ProtehPage(
    url: String,
    private val filePath: String,
    private val targetFolder: String,
    private val collection: ProtehCollection,
    private val header: ProtehHeader? = null,
    beforeDownload: ((driver: ChromeDriver) -> Unit)? = null
) {

    private val remotePage = RemotePage(url, beforeDownload)

    fun download() {
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
            throw Exception("AAA")
        }
        file.createNewFile()
        remotePage.download(filePath)
    }

    fun save() {
        val folder = File(targetFolder)
        if (folder.exists()) {
            folder.deleteRecursively()
        }
        if (header != null) {
            TextFile(header).save("$targetFolder/header.txt")
            runBlocking {
                Files(header).download("$targetFolder/gallery")
            }
        }
        collection.filePath = filePath
        OCExcelWb(collection).save("$targetFolder/products.xls")
        runBlocking {
            Files(collection).download("$targetFolder/images")
        }
    }
}