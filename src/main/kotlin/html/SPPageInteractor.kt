import org.openqa.selenium.By
import org.openqa.selenium.ElementClickInterceptedException
import org.openqa.selenium.chrome.ChromeDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SPPageInteractor(private val driver: ChromeDriver) {

    fun click(cssSelector: String) {
        driver.findElement(By.cssSelector(cssSelector)).click()
    }
    fun click(cssSelector: String, index: Int) {
        while (true) {
            try {
                driver.findElements(By.cssSelector(cssSelector))[index].click()
                break
            } catch (e: ElementClickInterceptedException) {
                Logger.error(e.message, e)
                Thread.sleep(1000)
            }
        }
    }

    companion object {
        val Logger = LoggerFactory.getLogger(this::class.java.name.substringBefore("\$Companion"))
    }
}