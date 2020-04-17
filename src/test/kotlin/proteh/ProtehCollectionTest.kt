package proteh

import model.RemoteFile
import model.Product
import org.jsoup.Jsoup
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

internal class ProtehCollectionTest {

    data class TestData(
        val input: Int,
        val expected: Product
    )

    @ParameterizedTest
    @MethodSource("productProvider")
    fun getProductsAllProducts(data: TestData) {
        val file = File("src/test/resources/argo.html")
        val doc = Jsoup.parse(file, "WINDOWS-1251")
        val products = ProtehCollection("Программа техно", 1, 50, doc)
        val actual = products.products[data.input]

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
                    name = "Комплект стеклянных дверей",
                    model = "А-302.С.Ф",
                    description = "Комплект стеклянных дверей А-302.С.Ф прозрачный. Подходит к шкафу А-302. Фурнитура в комплекте.",
                    manufacturer = "Программа техно",
                    category = "Шкафы",
                    categoryId = 50,
                    length = 71.0,
                    width = 0.4,
                    height = 76.0,
                    price = 1783.toBigDecimal(),
                    images = listOf(RemoteFile("https://proteh.ru/products/img/00000007831.png", "a-302_s_f_1")),
                    sku = "00000007831",
                    seo = "komplekt_steklyannykh_dverey-a-302_s_f-1"
                )
            ),
            TestData(input = 1,
                expected = Product(
                    productId = 2,
                    name = "Комплект стеклянных дверей",
                    model = "А-302.С.Ф",
                    description = "Комплект стеклянных дверей А-302.С.Ф тонированный. Подходит к шкафу А-302. Фурнитура в комплекте.",
                    manufacturer = "Программа техно",
                    category = "Шкафы",
                    categoryId = 50,
                    length = 71.0,
                    width = 0.4,
                    height = 76.0,
                    price = 2780.toBigDecimal(),
                    images = listOf(RemoteFile("https://proteh.ru/products/img/00000007832.png", "a-302_s_f_2")),
                    sku = "00000007832",
                    seo = "komplekt_steklyannykh_dverey-a-302_s_f-2"
                )
            ),
            TestData(input = 10,
                expected = Product(
                    productId = 11,
                    name = "Стол рабочий",
                    model = "А-002",
                    description = "Стол рабочий А-002 ясень шимо.",
                    manufacturer = "Программа техно",
                    category = "Столы",
                    length = 120.0,
                    categoryId = 50,
                    width = 73.0,
                    height = 76.0,
                    price = 2809.toBigDecimal(),
                    images = listOf(RemoteFile("https://proteh.ru/products/img/F0000003659.png", "a-002_11")),
                    sku = "F0000003659",
                    seo = "stol_rabochiy-a-002"
                )
            ),
            TestData(input = 100,
                expected = Product(
                    productId = 101,
                    name = "Стол на металлокаркасе",
                    model = "АМ-002",
                    description = "Стол на металлокаркасе АМ-002 ясень шимо.",
                    manufacturer = "Программа техно",
                    category = "Столы",
                    categoryId = 50,
                    length = 120.0,
                    width = 73.0,
                    height = 76.0,
                    price = 7717.toBigDecimal(),
                    images = listOf(RemoteFile("https://proteh.ru/products/img/F0000009577.png", "am-002_101")),
                    sku = "F0000009577",
                    seo = "stol_na_metallokarkase-am-002"
                )
            ),
            TestData(input = 150,
                expected = Product(
                    productId = 151,
                    name = "Стол переговорный на металлокаркасе",
                    model = "АМ-004.123",
                    description = "Стол переговорный на металлокаркасе АМ-004.123 ясень шимо/антрацит.",
                    manufacturer = "Программа техно",
                    category = "Столы",
                    categoryId = 50,
                    length = 160.0,
                    width = 123.6,
                    height = 76.0,
                    price = 0.toBigDecimal(),
                    images = listOf(),
                    sku = "F0000014743",
                    seo = "stol_peregovornyy_na_metallokarkase-am-004_123"
                )
            ),
            TestData(input = 143,
                expected = Product(
                    productId = 144,
                    name = "Бенч двойной на 6 рабочих мест",
                    model = "АМБ-005.60-6",
                    description = "Бенч двойной на 6 рабочих мест АМБ-005.60-6 ясень шимо.",
                    manufacturer = "Программа техно",
                    category = "Столы",
                    categoryId = 50,
                    length = 540.0,
                    width = 123.6,
                    height = 76.0,
                    price = 0.toBigDecimal(),
                    images = listOf(RemoteFile("https://proteh.ru/products/img/F0000011792.png", "amb-005_60-6_144")),
                    sku = "F0000011792",
                    seo = "bench_dvoynoy_na_6_rabochikh_mest-amb-005_60-6"
                )
            )
        )
    }
}