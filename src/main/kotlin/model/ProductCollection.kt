package model

import SPCachedPage
import SPCachedRemoteFiles
import SPPageInteractor
import SPRemoteFile
import SPSimplePage
import SPSimpleRemoteFiles
import kotlinx.coroutines.runBlocking
import opencart.OCExcelWb

class ProductCollection (
    val startId: Long,
    val subcategories: List<Subcategory>,
    val categoryCode: String,
    val categoryId: Long,
    val manufacturer: Manufacturer,
    val code: String
) {

    val imageFolders = subcategories.map { "/catalog/$categoryCode/$code/${it.code}" }

    private var currentProductId = startId
    private var addedSeo = mutableSetOf<String>()

    fun product(
        image: Image,
        seo: String,
        name: String,
        model: String,
        price: Price = Price.ZERO,
        sku: String = "",
        description: String,
        size: Size = Size(),
        weight: Double = 0.0,
        subcategory: String
    ) : Product {
        return Product(
            productId = currentProductId++,
            collection = this,
            image = image,
            seo = calculateSeo(seo),
            width = size.width,
            height = size.height,
            length = size.length,
            weight = weight,
            name = name,
            model = model,
            price = price,
            sku = sku,
            description = description,
            subcategory = subcategory
        )
    }

    private fun calculateSeo(seo: String): String {
        if (addedSeo.contains(seo)) {
            return calculateSeo("$seo-1")
        }
        addedSeo.add(seo)
        return seo
    }

    fun download(url: String, uniqName: String) {
        val page = SPCachedPage(SPSimplePage(url))

        subcategories.forEach {
            val doc = page
                .onLoad { interactor: SPPageInteractor -> manufacturer.onLoad?.invoke(interactor, it.index) }
                .save("./result/${uniqName}/${it.code}.html")

            val products = manufacturer.parser.parse(doc, this)
            val remoteFiles = SPSimpleRemoteFiles(products.map { product -> SPRemoteFile(product.image.url, product.image.fileName) })

            runBlocking {
                SPCachedRemoteFiles(remoteFiles).download("./result/${uniqName}/${it.code}")
            }

            if (subcategories.indexOf(it) == 0) {
                OCExcelWb(products).save("./result/${uniqName}.xls")
            }
        }
    }
}