package proteh

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import remote.Page

internal class SaveArgo {

    @Test
    @Disabled
    fun download() {
        val download = Page("https://www.proteh.ru/argo/")
        download.download("./src/test/resources/argo.html")
    }

    @Test
    @Disabled
    fun save() {
        val page = ProtehPage("./src/test/resources/argo.html")
        page.save()
    }
}