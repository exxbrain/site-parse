package proteh

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ProtehHeaderTest {

    @Test
    fun getGalleryImages() {
        val header = ProtehHeader("https://proteh.ru/argo")
        val actual = header.getImages("src/test/resources/argo.html")

        assertEquals(45, actual.size)
        assertEquals("https://proteh.ru/argo/img/int/argo%20(10).jpg", actual[0].url)
    }
}