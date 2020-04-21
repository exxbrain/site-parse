package model

import files.RemoteFile

abstract class Products {
    private var _products: List<Product>? = null

    fun getProducts(filePath: String): List<Product> {
        if (_products == null) {
            _products = init(filePath)
        }
        return _products!!;
    }

    fun getImages(filePath: String): List<RemoteFile> {
        return getProducts(filePath).flatMap { it.images };
    }

    private fun init(filePath: String) : List<Product> {
        val products = getProductsInternal(filePath)

        products
            .groupBy { it.seo }
            .filter { it.value.size > 1 }
            .forEach { entry ->
                entry.value.forEach {
                    it.seo = "${it.seo}-${it.productId}"
                }
            }

        products
            .groupBy { it.imageName }
            .filter { it.value.size > 1 }
            .forEach { entry ->
                entry.value.forEach {
                    val imageName = "${it.imageName}-${it.productId}"
                    it.imageName = imageName
                    it.images[0].fileNameWithoutExtension = imageName
                }
            }

        return products
    }

    protected abstract fun getProductsInternal(filePath: String) : List<Product>
}