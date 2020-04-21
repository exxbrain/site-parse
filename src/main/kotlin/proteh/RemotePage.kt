package proteh

import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset

class RemotePage(
    private val url: String,
    private val beforeDownload: ((driver: ChromeDriver) -> Unit)? = null
) {
    fun download(targetPath: String) {
        val driver = ChromeDriver()
        driver.get(url)
        beforeDownload?.let { it(driver) }
        val pageSource = driver.pageSource
        FileOutputStream(File(targetPath)).use {
            it.write(pageSource.toByteArray(Charset.forName("WINDOWS-1251")))
        }
        driver.close()
    }
}