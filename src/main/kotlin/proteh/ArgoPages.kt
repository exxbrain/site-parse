package proteh

import model.Page
import org.openqa.selenium.By

class ArgoPages {
    companion object {

        private val imageFolder = "korpusnaya_mebel/operativnaya_mebel/argo"
        private val baseUrl = "https://www.proteh.ru/argo"

        val yasenShimo = Page(
            url = baseUrl,
            code = "yasen-shimo",
            filePath = "./src/test/resources/argo.html",
            targetFolder = "./result/argo/yasen-shimo",
            products = ProtehProducts(
                startId = 450,
                categoryId = 67,
                options = linkedMapOf("Цвет" to listOf(
                    "Ясень шимо",
                    "Гарбо",
                    "Белый",
                    "Груша арозо",
                    "Серый",
                    "Бук",
                    "Дуб венге",
                    "Орех",
                    "Синий"
                )),
                additionalImageFolders = listOf(
                    "catalog/$imageFolder/garbo",
                    "catalog/$imageFolder/belyi",
                    "catalog/$imageFolder/grusha_arozo",
                    "catalog/$imageFolder/seryi",
                    "catalog/$imageFolder/buk",
                    "catalog/$imageFolder/dub_venge",
                    "catalog/$imageFolder/oreh",
                    "catalog/$imageFolder/siniy"
                ),
                imageFolder = "catalog/$imageFolder/yasen-shimo"
            ),
            header = ProtehHeader(
                baseUrl = baseUrl
            )
        )

        val garbo = page("garbo", 1)
        val white = page("belyi", 2)
        val grusha_arozo = page("grusha_arozo", 3)
        val seryi = page("seryi", 4)
        val buk = page("buk", 5)
        val dub_venge = page("dub_venge", 6)
        val oreh = page("oreh", 7)
        val siniy = page("siniy", 8)

        private fun page(code: String, colorNum: Int): Page {
            return Page(
                url = baseUrl,
                code = code,
                filePath = "./src/test/resources/argo-$code.html",
                targetFolder = "./result/argo/$imageFolder/$code",
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
            yasenShimo.download()
            garbo.download()
            grusha_arozo.download()
            buk.download()
            white.download()
            dub_venge.download()
            oreh.download()
            seryi.download()
            siniy.download()
        }

        fun saveAll() {
            yasenShimo.save()
            garbo.save()
            grusha_arozo.save()
            buk.save()
            white.save()
            dub_venge.save()
            oreh.save()
            seryi.save()
            siniy.save()
        }
    }
}