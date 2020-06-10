package proteh

import model.Page
import org.openqa.selenium.By

class AccentPages {
    companion object {

        private val imageFolder = "korpusnaya_mebel/kabinet/akcent"
        private val baseUrl = "https://www.proteh.ru/akcent"


        val oreh_paldao = Page(
            url = baseUrl,
            code = "oreh_paldao",
            filePath = "./src/test/resources/akcent.html",
            targetFolder = "./result/akcent/$imageFolder/oreh_paldao",
            products = ProtehProducts(
                startId = 700,
                categoryId = 92,
                options = linkedMapOf("Цвет" to listOf(
                    "Орех палдао",
                    "Дуб светлый",
                    "Орех артисан"
                )),
                additionalImageFolders = listOf(
                    "catalog/$imageFolder/dub_svetlyi",
                    "catalog/$imageFolder/oreh_artisan"
                ),
                imageFolder = "catalog/$imageFolder/oreh_paldao"
            ),
            header = ProtehHeader(
                baseUrl = baseUrl
            )
        )

        val dub = page("dub_svetlyi", 1)
        val oreh_artisan = page("oreh_artisan", 2)

        private fun page(code: String, colorNum: Int): Page {
            return Page(
                url = baseUrl,
                code = code,
                filePath = "./src/test/resources/akcent-$code.html",
                targetFolder = "./result/akcent/$imageFolder/$code",
                products = ProtehProducts(
                    250,
                    67,
                    imageFolder = "catalog/$imageFolder/$code"
                ),
                beforeDownload = {
                    val element = it.findElements(By.cssSelector("#colors div"))[colorNum]
                    element.click()
                }
            )
        }

        fun downloadAll() {
            oreh_paldao.download()
            dub.download()
            oreh_artisan.download()
        }

        fun saveAll() {
            oreh_paldao.save(true)
            dub.save()
            oreh_artisan.save()
        }
    }
}