package riva

import model.Subcategories
import model.Manufacturer
import org.junit.jupiter.api.Test

internal class DownloadCollectionTest {

    @Test
    fun getProductsAllProducts() {
        Manufacturer("Riva")
            .selectSubcategory { interactor, index ->
                interactor.click(".variants__label", index)
                interactor.click("#custom_tp")
                try {
                    while (true) {
                        interactor.click(".show-more-psevdo")
                    }
                } catch (e: Exception) {
                }
            }
            .parser(RivaProductParser())
            .download(
                startId = 1500,
                categoryId = 98,
                subcategories = Subcategories.subcategories("Венге цаво", "Бук артиан", "Орех гварнери"),
                categoryCode = "korpusnaya_mebel/operativnaya_mebel",
                uniqName = "riva/nova_s",
                url = "https://riva.ru/catalog/nova_s/"
            )
            .download(
                startId = 1700,
                categoryId = 99,
                subcategories = Subcategories.subcategories("Клен", "Венге цаво", "Белый", "Груша ароза", "Клен металлик", "Орех гварнери", "Серый", "Венге металлик"),
                categoryCode = "korpusnaya_mebel/operativnaya_mebel",
                uniqName = "riva/riva",
                url = "https://riva.ru/catalog/riva/"
            )
            .download(
                startId = 2000,
                categoryId = 100,
                subcategories = Subcategories.subcategories(
                    "Акация-Антрацит",
                    "Акация-Белый",
                    "Акация-Мокко",
                    "Акация-Серый",
                    "Белый-Антрацит",
                    "Белый-Белый",
                    "Белый-Мокко",
                    "Белый-Серый",
                    "Венге цаво-Антрацит",
                    "Венге цаво-Белый",
                    "Венге цаво-Мокко",
                    "Венге цаво-Серый",
                    "Вяз-Антрацит",
                    "Вяз-Белый",
                    "Вяз-Мокко",
                    "Вяз-Серый",
                    "Дуб наварра-Антрацит",
                    "Дуб наварра-Белый",
                    "Дуб наварра-Мокко",
                    "Дуб наварра-Серый",
                    "Венге цаво",
                    "Белый",
                    "Акация лорка",
                    "Вяз благородный",
                    "Дуб наварра",
                    ),
                categoryCode = "korpusnaya_mebel/operativnaya_mebel",
                uniqName = "riva/metal_system",
                url = "https://riva.ru/catalog/metal_system/"
            )
            .download(
                startId = 2300,
                categoryId = 101,
                subcategories = Subcategories.subcategories(
                    "Дуб табак",
                    "Венге цаво",
                    "Клен",
                ),
                categoryCode = "korpusnaya_mebel/kabinet",
                uniqName = "riva/first",
                url = "https://riva.ru/catalog/first1/"
            )
            .download(
                startId = 2400,
                categoryId = 102,
                subcategories = Subcategories.subcategories(
                    "Акация лорка",
                    "Вяз благородный",
                    "Суар Темный",
                    "Снежная Патина",
                ),
                categoryCode = "korpusnaya_mebel/kabinet",
                uniqName = "riva/yalta",
                url = "https://riva.ru/catalog/yalta/"
            )
            .download(
                startId = 2800,
                categoryId = 104,
                subcategories = Subcategories.subcategories(
                    "Серый",
                    "Венге металлик",
                    "Клен металлик",
                ),
                categoryCode = "korpusnaya_mebel/reseptions",
                uniqName = "riva/reseptions-riva",
                url = "https://riva.ru/catalog/riva1/"
            )
    }

    fun getImages() {

    }
}