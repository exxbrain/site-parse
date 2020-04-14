package proteh

import model.Header
import model.RemoteFile
import org.jsoup.nodes.Document

class ProtehHeader(private val baseUrl: String, private val doc: Document): Header {

    override val galleryImages: List<RemoteFile>
        get() {
            return doc
                .select(".gallery-top .swiper-slide")
                .mapIndexed { index, element ->
                    val str = element.attr("style")
                    val url = Regex("url\\(\"(.*)\"\\)").find(str)!!.groupValues[1].replace(" ", "%20")
                    RemoteFile("$baseUrl/$url", index.toString().padStart(5, '0'))
                }
        }

    override val text: String
        get() {
            return doc.select("#info").html()
        }

}