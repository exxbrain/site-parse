package riva

import model.*
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class RivaProductParser : Parser {

    private fun parse(element: Element, category: String, collection: ProductCollection): Product {
        val img = element.selectFirst("div[class='card__img']")
        val htmlStr = element.html()
        val imgUrl = img.attr("style")
            .replace("background-image: url(", "")
            .replace(");", "")
            .replace("\"", "")
        val name = element.getElementsByClass("product__name").first().text()
        val articul = htmlStr.find(Regex("Артикул:<span>(.*)</span>"))!!
        var size = Size()
        htmlStr.find(Regex("размеры: <span>(\\d{1,4}х\\d{1,4}х\\d{1,4})"))
            ?.split("х")
            ?.let {
                size = Size(it[0].toDouble() / 10, it[1].toDouble() / 10, it[2].toDouble() / 10)
            }
        return collection.product(
            image = Image("https://riva.ru$imgUrl", articul.urlize()),
            name = name,
            model = articul,
            seo = "${name}-${articul}".urlize(),
            size = size,
            description = name,
            category = category
        )
    }

    override fun parse(document: Document, collection: ProductCollection): List<Product> {
        return document.select(".cards__window-div").flatMap {
            val category = it.selectFirst(".cards__window-subt").text().capitalize()
            it.select(".card__this").map { element ->
                parse(element, category, collection)
            }
        }
    }
}