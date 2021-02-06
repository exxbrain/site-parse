package model

import SPCachedPage
import SPCachedRemoteFiles
import SPPageInteractor
import SPRemoteFile
import SPSimplePage
import SPSimpleRemoteFiles
import kotlinx.coroutines.runBlocking
import opencart.OCExcelWb

class ProductCollection(val url: String,
                        val uniqName: String,
                        val colors: List<Color>,
                        val categoryCode: String,
                        val categoryId: Long) {

    fun download() {
        val page = SPCachedPage(SPSimplePage(url))

        colors.forEach {
            val doc = page
                .onLoad { interactor: SPPageInteractor -> onLoad(interactor, it.index) }
                .save("./src/test/resources/${uniqName}/${it.code}.html")

            val products = parser.parse(doc, this)
            val remoteFiles = SPSimpleRemoteFiles(products.map { product -> SPRemoteFile(product.image.url, product.image.fileName) })

            runBlocking {
                SPCachedRemoteFiles(remoteFiles).download("./src/test/resources/${uniqName}/${it.code}")
            }

            if (colors.indexOf(it) == 0) {
                OCExcelWb(products).save("./src/test/resources/${uniqName}/${uniqName}.xls")
            }
        }
    }
}