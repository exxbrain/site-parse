package opencart

import com.ibm.icu.text.Transliterator
import excel.WithData
import model.Product
import java.util.LinkedHashMap

class OpenCartProductSeoKeywords(private val product: Product) : WithData {
    private val toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN")

    override fun getData(): LinkedHashMap<String, Any> {
        val seo = toLatinTrans.transliterate("${product.name}-${product.model}")
            .toLowerCase()
            .replace(".", "_")
            .replace("/", "_")
            .replace(" ", "_")
            .replace("ʹ", "_")
        return linkedMapOf(
            "product_id" to product.productId,
            "store_id" to 0,
            "keyword(ru-ru)" to seo
        )
    }

}
