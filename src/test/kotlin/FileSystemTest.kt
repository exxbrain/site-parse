import io.mockk.impl.annotations.MockK
import model.Collection
import model.RemoteFile
import opencart.OpenCartFolder
import excel.WithData
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File
import java.util.LinkedHashMap

internal class FileSystemTest {

    @MockK
    lateinit var document: Collection

    @AfterEach
    fun afterEach () {
        File("./test").deleteRecursively()
    }

    @Test
    fun saveImages() {
        val fileSystem = OpenCartFolder("./test/argo")

        val imgFolder = "korpusnaya_mebel/operativnaya_mebel/argo/"
        val image =
            RemoteFile("http://www.oc.ralfinter.ru/image/cache/catalog/Жалюзи-вместо-штор-1140x380.jpg")
        runBlocking {
            fileSystem.save(image, imgFolder)
        }
        assertTrue(File("./test/argo/korpusnaya_mebel/operativnaya_mebel/argo/${image.fileName}").exists())
    }

    @Test
    fun saveData() {
        val sut = OpenCartFolder("./test/argo")
        val data = linkedMapOf(
            "Products" to listOf<WithData>(
                object: WithData {
                    override fun getData(): LinkedHashMap<String, Any> {
                        return linkedMapOf(
                            "product_id" to 23455,
                            "number" to 1234
                        )
                    }
                }
            )
        )
        sut.save(data, "import.xls")
        assertTrue(File("./test/argo/import.xls").exists())
    }

    @Test
    fun saveText() {
        val sut = OpenCartFolder("./test/argo")
        sut.save("test", "text.txt")
        assertTrue(File("./test/argo/text.txt").exists())
    }
}