package riva

import org.junit.jupiter.api.Test

internal class DownloadRivaCollectionTest {

    @Test
    fun getProductsAllProducts() {
        val collection = DownloadRivaCollection()
        collection.download("nova_s")
    }
}