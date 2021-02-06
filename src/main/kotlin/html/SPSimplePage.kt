import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.StandardOpenOption

class SPSimplePage(private val url: String, internal val charset: String = "UTF-8"): SPPage {

    class DriverProblemException(message: String, cause: Throwable) : Exception(message, cause)

    private var onLoad: ((pageInteractor: SPPageInteractor) -> Unit)? = null
    lateinit var filePath: String

    override fun onLoad(onLoad: (pageInteractor: SPPageInteractor) -> Unit): SPSimplePage {
        this.onLoad = onLoad
        return this
    }

    override fun save(filePath: String): Document {
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
        lateinit var driver: ChromeDriver
        try {
            driver = ChromeDriver(options)
        } catch (e: IllegalStateException) {
            throw DriverProblemException("Couldn't initialize Chrome Driver!!! ${e.message}", e)
        }
        driver.get(url)
        onLoad?.let { it(SPPageInteractor(driver)) }
        val pageSource = driver.pageSource
        Files.write(File(filePath).toPath(),
            pageSource.toByteArray(Charset.forName(charset)),
            StandardOpenOption.CREATE)
        driver.close()
        this.filePath = filePath
        return Jsoup
            .parse(File(this.filePath), charset)
    }
}
