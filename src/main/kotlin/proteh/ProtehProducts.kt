package proteh

import com.ibm.icu.text.Transliterator
import files.RemoteFile
import model.Product
import model.Products
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File
import java.math.BigDecimal

class ProtehProducts(
    startId: Long = 1,
    private val categoryId: Long = 1,
    private val options: LinkedHashMap<String, List<String>> = linkedMapOf(),
    private val additionalImageFolders: List<String> = listOf(),
    private val imageFolder: String
) : Products() {

    private var productId = startId
    private val toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN")

    private fun createProduct(element: Element): Product {
        val str = element.toString()

        val a = element.select("a").first()
        val dataCaption = a.attr("data-caption")
        val description = Regex(".*Описание:<br>(.*)<br>").find(dataCaption)?.groupValues?.last()

        val imageUrl = a.attr("href")
        val articul = element.attr("data-art")

        var imageName = toLatinTrans.transliterate(articul)
        imageName = imageName.toLowerCase().replace(Regex("[^a-z0-9-]"), "_")
        val image = RemoteFile(imageUrl, imageName)

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
            productId = productId++,
            name = name,
            images = if (imageUrl == "/img/noImage.png") listOf() else listOf(image),
            manufacturer = "Программа техно",
            model = articul,
            description = "${element.attr("data-name")}. $description".trim(),
            imageName = imageName,
            price = priceBigDecimal,
            length = length,
            width = width,
            height = height,
            category = element.attr("data-cat"),
            categoryId = categoryId,
            sku = element.attr("data-id"),
            seo = seo,
            options = options,
            additionalImages = additionalImageFolders.map { "$it/${image.fileName}" },
            imageFolder = imageFolder
        )
    }

    override fun getProductsInternal(filePath: String): List<Product> {
        val document = Jsoup.parse(File(filePath), "WINDOWS-1251")
        return document
            .select(".muuri-item")
            .sortedBy { it.attr("data-art") }
            .map{ createProduct(it) }
    }
}