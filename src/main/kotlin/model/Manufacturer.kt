package model

import SPPageInteractor


/**
 * Fabric represents web site
 *
 * @property categoryCode category code for example operativnaya_mebel
 */
class Fabric(
    val manufacturer: String,
    val onLoad: ((interactor: SPPageInteractor, colorIndex: Int) -> Unit)? = null,
    val parser: Parser?
) {
    fun collection (
        startId: Long,
        url: String,
        uniqName: String,
        colors: List<Color>,
        categoryCode: String,
        categoryId: Long) : ProductCollection {
        return ProductCollection(
            startId = startId,
            url = url,
            fabric = this,
            uniqName = uniqName,
            colors = colors,
            categoryCode = categoryCode,
            categoryId = categoryId
        )
    }
}
