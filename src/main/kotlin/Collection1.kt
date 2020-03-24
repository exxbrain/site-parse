import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.FileOutputStream

class Collection1(private val url: String, private val folderName: String)
{
    private fun mkDirIfNeeded() {
        val folder = File("./${folderName}")
        if (!folder.exists() && !folder.mkdirs()) {
            throw Exception("AAA")
        }
    }

    private fun saveText(fileName: String, text: String) {
        mkDirIfNeeded()
        val file = File("./${folderName}/${fileName}")
        val stream = FileOutputStream(file)
        stream.use { s -> s.write(text.toByteArray()) }
    }

    private fun getThumbUrl(x: Element): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getImageUrl(x: Element): String {
        return "test"
    }

    fun save() {
        val doc = Document(url)
        saveText("header.txt", doc.select("#info").html())
        doc.select("#gallery").select(".swiper-slide").forEach { x ->
            // Picture(getImageUrl(x), getThumbUrl(x)).save(folderName)
        }
    }
}