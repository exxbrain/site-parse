package model

import SPPageInteractor


/**
 * Fabric represents web site
 *
 * @property categoryCode category code for example operativnaya_mebel
 */
class Manufacturer(val name: String) {
    var onLoad: ((interactor: SPPageInteractor, colorIndex: Int) -> Unit)? = null
        private set

    lateinit var parser: Parser
        private set

    fun selectSubcategory(onLoad: (interactor: SPPageInteractor, colorIndex: Int) -> Unit): Manufacturer {
        this.onLoad = onLoad
        return this
    }

    fun parser(parser: Parser): Manufacturer {
        this.parser = parser
        return this
    }

    fun download (
        startId: Long,
        url: String,
        uniqName: String,
        subcategories: List<Subcategory>,
        categoryCode: String,
        categoryId: Long) : Manufacturer {

        ProductCollection(
            startId = startId,
            manufacturer = this,
            subcategories = subcategories,
            code = uniqName,
            categoryCode = categoryCode,
            categoryId = categoryId
        ).download(url, uniqName)

        return this
    }
}
