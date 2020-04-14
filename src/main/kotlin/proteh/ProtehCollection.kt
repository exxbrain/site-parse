package proteh

import com.ibm.icu.text.Transliterator
import model.Collection
import model.Product
import model.RemoteFile
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.math.BigDecimal


class ProtehCollection(private val manufacturer: String, private val startId: Long, private val doc: Document) : Collection {

    private val toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN")
    override val products: List<Product>
        get() {
            return doc
                .select(".muuri-item")
                .mapIndexed{ index, element ->  createProduct(index + startId, element) }
        }

    private fun createProduct(productId: Long, element: Element): Product {
        val str = element.toString()

        val a = element.select("a").first()
        val dataCaption = a.attr("data-caption")
        val description = Regex(".*Описание:<br>(.*)<br>").find(dataCaption)?.groupValues?.last()

        val imageUrl = a.attr("href")
        val articul = element.attr("data-art")

        var imageName = toLatinTrans.transliterate(articul)
            .replace(".", "_")
            .replace("/", "_")

        imageName = "${imageName}_${element.attr("data-id")}".toLowerCase()
        val images = if (imageUrl == "/img/noImage.png") listOf() else listOf(RemoteFile(imageUrl, imageName))

        val sizes = Regex("(\\d+)x(\\d+)x(\\d+)").find(str)?.groupValues
        var length = 0.0
        var width = 0.0
        var height = 0.0
        if (sizes != null) {
            length = sizes[1].toDouble() / 10
            width = sizes[2].toDouble() / 10
            height = sizes[3].toDouble() / 10
        }

        val price = Regex("(\\d+) руб\\.").find(str)?.groupValues
        var priceBigDecimal = BigDecimal.ZERO
        if (price != null) {
            priceBigDecimal = price[1].toBigDecimal()
        }

        return Product(
            productId = productId,
            name = element.attr("data-shortname").trim(),
            images = images,
            manufacturer = manufacturer,
            model = articul,
            description = "${element.attr("data-name")}. $description".trim(),
            price = priceBigDecimal,
            length = length,
            width = width,
            height = height,
            category = element.attr("data-cat"),
            sku = element.attr("data-id")
        )
    }
}