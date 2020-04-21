package files

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class FilesTest {

    @AfterEach
    fun afterEach () {
        File("./test").deleteRecursively()
    }

    @Test
    fun saveImages() {
        val image =
            RemoteFile("http://www.oc.ralfinter.ru/image/cache/catalog/Жалюзи-вместо-штор-1140x380.jpg")
        runBlocking {
            Files(image).download("./test/argo/korpusnaya_mebel/operativnaya_mebel/argo/")
        }
        assertTrue(File("./test/argo/korpusnaya_mebel/operativnaya_mebel/argo/${image.fileName}").exists())
    }
}