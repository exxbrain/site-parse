package proteh

import org.jsoup.Jsoup
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class ProtehHeaderTest {

    @Test
    fun getGalleryImages() {
        val file = File("src/test/resources/argo.html")
        val header = ProtehHeader("https://proteh.ru/argo", Jsoup.parse(file, "WINDOWS-1251"))
        val actual = header.galleryImages

        assertEquals(45, actual.size)
        assertEquals("https://proteh.ru/argo/img/int/argo%20(10).jpg", actual[0].url)
    }

    @Test
    fun getText() {
        val file = File("src/test/resources/argo.html")
        val header = ProtehHeader("https://proteh.ru/argo", Jsoup.parse(file, "WINDOWS-1251"))
        val actual = header.text

        val expected = "Серия офисной мебели «Арго» имеет большой элементный ряд, позволяющий выбрать тот тип и размер мебели, который подойдет именно Вам. В производстве офисной мебели серии «Арго» используется экологически чистая ламинированная ДСП с меламиновым покрытием.\n" +
                "<br> Столы изготовлены полностью из ЛДСП толщиной 22 мм.\n" +
                "<br> Каркасы шкафов и стеллажей — ЛДСП 22 мм.\n" +
                "<br> Экраны, накладки ящиков тумб, двери шкафов изготовлены из ЛДСП толщиной 18 мм.\n" +
                "<br> Торцы облицованы противоударной кромкой ПВХ толщиной 2 мм, в цвет корпуса ЛДСП. Сборка деталей осуществляется на эксцентриковые стяжки, что позволяет многократно разбирать и собирать мебель при перемещении и переездах.\n" +
                "<br> Мебель соответствует ГОСТ-16371-2014.\n" +
                "<br> \n" +
                "<br> <a href=\"/news/detail.php?ID=1006\">Внимание! Патент АРГО</a>"

        assertEquals(expected, actual)
    }
}