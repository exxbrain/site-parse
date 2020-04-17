package proteh

import com.ibm.icu.text.Transliterator
import model.Collection
import model.Product
import model.RemoteFile
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.math.BigDecimal


class ProtehCollection(private val manufacturer: String, private val startId: Long, private val categoryId: Long, private val doc: Document) : Collection {

    private val toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN")
    override val products: List<Product>
        get() {
            val products = doc
                .select(".muuri-item")
                .mapIndexed{ index, element ->  createProduct(index, element) }

            products
                .groupBy { it.seo }
                .filter { it.value.size > 1 }
                .forEach { entry ->
                    entry.value.forEach {
                        it.seo = "${it.seo}-${it.productId}"
                    }
                }
            return products
        }

    private fun createProduct(index: Int, element: Element): Product {
        val str = element.toString()
        val productId = index + startId

        val a = element.select("a").first()
        val dataCaption = a.attr("data-caption")
        val description = Regex(".*Описание:<br>(.*)<br>").find(dataCaption)?.groupValues?.last()

        val imageUrl = a.attr("href")
        val articul = element.attr("data-art")

        var imageName = toLatinTrans.transliterate(articul)
        imageName = "${imageName}_$productId".toLowerCase().replace(Regex("[^a-z0-9-]"), "_")
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

        val name = element.attr("data-shortname").trim()

        val seo = toLatinTrans.transliterate("${name}-${articul}")
            .toLowerCase()
            .replace(Regex("[^a-z0-9-]"), "_")

        return Product(
            productId = productId,
            name = name,
            images = images,
            manufacturer = manufacturer,
            model = articul,
            description = "${element.attr("data-name")}. $description".trim(),
            price = priceBigDecimal,
            length = length,
            width = width,
            height = height,
            category = element.attr("data-cat"),
            categoryId = categoryId,
            sku = element.attr("data-id"),
            seo = seo
        )
    }
}