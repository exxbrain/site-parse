package remote

import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset

class Page(private val url: String) {
    fun download(filePath: String) {
        val driver = ChromeDriver()
        driver.get(url)
        val pageSource = driver.pageSource
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
            throw Exception("AAA")
        }
        file.createNewFile()
        FileOutputStream(file).use {
            it.write(pageSource.toByteArray(Charset.forName("WINDOWS-1251")))
        }
        driver.close()
    }
}