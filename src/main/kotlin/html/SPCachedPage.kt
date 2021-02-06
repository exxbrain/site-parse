import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File

/**
 * A cached page.
 *
 * This class is a decorator for simple page.
 *
 * @param page the page, that should be decorated
 * @constructor Creates a decorated page.
 */
class SPCachedPage(private val page: SPSimplePage): SPPage {

    override fun onLoad(onLoad: (pageInteractor: SPPageInteractor) -> Unit): SPCachedPage {
        page.onLoad(onLoad)
        return this
    }

    override fun save(filePath: String): Document {
        val file = File(filePath)
        if (!file.exists()) {
            page.save(filePath)
        }
        page.filePath = filePath
        return Jsoup.parse(File(filePath), page.charset)
    }
}