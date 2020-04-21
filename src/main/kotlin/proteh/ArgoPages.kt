package proteh

import model.Page
import org.openqa.selenium.By

class ArgoPages {
    companion object {

        private val imageFolder = "./result/argo/korpusnaya_mebel/operativnaya_mebel/argo"
        private val baseUrl = "https://www.proteh.ru/argo"

        val yasenShimo = Page(
            url = baseUrl,
            filePath = "./src/test/resources/argo.html",
            targetFolder = "$imageFolder/yasen-shimo",
            products = ProtehProducts(
                startId = 250,
                categoryId = 90,
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
                    "$imageFolder/garbo",
                    "$imageFolder/belyi",
                    "$imageFolder/grusha_arozo",
                    "$imageFolder/seryi",
                    "$imageFolder/buk",
                    "$imageFolder/dub_venge",
                    "$imageFolder/oreh",
                    "$imageFolder/siniy"
                ),
                imageFolder = "$imageFolder/yasen-shimo"
            ),
            header = ProtehHeader(
                filePath = "./src/test/resources/argo.html",
                baseUrl = baseUrl
            )
        )

        val garbo = Page(
            url = baseUrl,
            filePath = "./src/test/resources/argo-garbo.html",
            targetFolder = "$imageFolder/yasen-shimo",
            products = ProtehProducts(
                250,
                90,
                imageFolder = "$imageFolder/garbo"
            ),
            beforeDownload = {
                val element = it.findElements(By.cssSelector("#colors div"))[1]
                element.click()
            }
        )

        val white = Page(
            url = baseUrl,
            filePath = "./src/test/resources/argo-white.html",
            targetFolder = "$imageFolder/white",
            products = ProtehProducts(
                250,
                90,
                imageFolder = "$imageFolder/white"
            ),
            beforeDownload = {
                val element = it.findElements(By.cssSelector("#colors div"))[2]
                element.click()
            }
        )
    }
}