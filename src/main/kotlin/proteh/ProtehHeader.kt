package proteh

import model.Header
import files.RemoteFile
import org.jsoup.Jsoup
import java.io.File

class ProtehHeader(
    private val filePath: String,
    private val baseUrl: String
): Header {

    override val text: String
        get() {
            val doc = Jsoup.parse(File(filePath), "WINDOWS-1251")
            return doc.select("#info").html()
        }

    override val images: List<RemoteFile>
        get() {
            val doc = Jsoup.parse(File(filePath), "WINDOWS-1251")
            return doc
                .select(".gallery-top .swiper-slide")
                .mapIndexed { index, element ->
                    val str = element.attr("style")
                    val url = Regex("url\\(\"(.*)\"\\)").find(str)!!.groupValues[1].replace(" ", "%20")
                    RemoteFile("$baseUrl/$url", index.toString().padStart(5, '0'))
                }
        }

}