package proteh

import kotlinx.coroutines.runBlocking
import opencart.OpenCartCollection
import opencart.OpenCartFolder
import org.jsoup.Jsoup
import java.io.File

class ProtehPage(private val filePath: String) {
    fun save() {
        val doc = Jsoup.parse(File(filePath), "WINDOWS-1251")

        val folder = OpenCartFolder("./result/argo")
        folder.clear()

        val header = ProtehHeader("https://www.proteh.ru/argo", doc)
        folder.save(header.text, "header.txt")
        runBlocking {
            folder.save(header.galleryImages, "korpusnaya_mebel/operativnaya_mebel/argo/gallery")
        }

        val collection = OpenCartCollection(ProtehCollection("Программа техно", 250, 90, doc))

        folder.save(collection.getData("korpusnaya_mebel/operativnaya_mebel/argo/yasen-shimo"), "products.xls")

        runBlocking {
            folder.save(collection.getImages(), "korpusnaya_mebel/operativnaya_mebel/argo/yasen-shimo")
        }
    }
}