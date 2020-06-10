package proteh

import files.RemoteFile
import model.Price
import model.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ProtehCollectionTest {

    data class TestData(
        val input: Int,
        val expected: Product
    )

    @ParameterizedTest
    @MethodSource("productProvider")
    fun getProductsAllProducts(data: TestData) {
        val products = ProtehProducts(
            startId = 1,
            categoryId = 50,
            options = linkedMapOf("Цвет" to listOf(
                "Ясень шимо",
                "Гарбо"
            )),
            additionalImageFolders = listOf(
                "operativnaya_mebel/argo/garbo",
                "operativnaya_mebel/argo/belyi"
            ),
            imageFolder = "operativnaya_mebel/argo/yasen-shimo"
        )
        val actual = products.getProducts("src/test/resources/argo.html")[data.input]

        assertEquals(data.expected.seo, actual.seo)
        assertEquals(data.expected.categoryId, actual.categoryId)
        assertEquals(data.expected.productId, actual.productId)
        assertEquals(data.expected.manufacturer, actual.manufacturer)
        assertEquals(data.expected.description, actual.description)
        assertEquals(data.expected.name, actual.name)
        assertEquals(data.expected.model, actual.model)
        assertEquals(data.expected.category, actual.category)
        assertEquals(data.expected.length, actual.length)
        assertEquals(data.expected.width, actual.width)
        assertEquals(data.expected.height, actual.height)
        assertEquals(data.expected.price, actual.price)
        if (!data.expected.images.any()) {
            assertTrue(!actual.images.any())
        } else {
            assertEquals(data.expected.images[0].url, actual.images[0].url)
            assertEquals(data.expected.images[0].fileName, actual.images[0].fileName)
        }
        assertEquals(data.expected.sku, actual.sku)
    }


    @Test
    fun getGalleryImages() {
    }

    @Test
    fun getText() {
    }

    companion object {
        @JvmStatic
        private fun productProvider() = Stream.of(
            TestData(input = 0,
                expected = Product(
                    productId = 1,
                    name = "Стол рабочий",
                    model = "А-001",
                    description = "Стол рабочий А-001 ясень шимо.",
                    manufacturer = "Программа техно",
                    category = "Столы",
                    categoryId = 50,
                    length = 90.0,
                    width = 73.0,
                    height = 76.0,
                    price = Price(2746),
                    images = listOf(
                        RemoteFile(
                            "https://proteh.ru/products/img/F0000003880.png",
                            "a-001"
                        )
                    ),
                    sku = "F0000003880",
                    seo = "stol_rabochiy-a-001",
                    imageFolder = "operativnaya_mebel/argo/yasen-shimo"
                )
            )
        )
    }
}