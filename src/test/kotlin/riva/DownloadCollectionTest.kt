package riva

import model.Colors
import model.Manufacturer
import org.junit.jupiter.api.Test

internal class DownloadCollectionTest {

    @Test
    fun getProductsAllProducts() {
        Manufacturer("Riva")
            .onLoad { interactor, index ->
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
                colors = Colors.colors("Венге цаво", "Бук артиан", "Орех гварнери"),
                categoryCode = "result/korpusnaya_mebel/operativnaya_mebel",
                uniqName = "riva/nova_s",
                url = "https://riva.ru/catalog/nova_s/"
            )
            .download(
                startId = 1700,
                categoryId = 99,
                colors = Colors.colors("Клен", "Венге цаво", "Белый", "Груша ароза", "Клен металлик", "Орех гварнери", "Серый", "Венге металлик"),
                categoryCode = "result/korpusnaya_mebel/operativnaya_mebel",
                uniqName = "riva/riva",
                url = "https://riva.ru/catalog/riva/"
            )
            .download(
                startId = 2000,
                categoryId = 100,
                colors = Colors.colors(
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
                categoryCode = "result/korpusnaya_mebel/operativnaya_mebel",
                uniqName = "riva/metal_system",
                url = "https://riva.ru/catalog/metal_system/"
            )
            .download(
                startId = 2300,
                categoryId = 101,
                colors = Colors.colors(
                    "Дуб табак",
                    "Венге цаво",
                    "Клен",
                ),
                categoryCode = "result/korpusnaya_mebel/kabinet",
                uniqName = "riva/first",
                url = "https://riva.ru/catalog/first1/"
            )
            .download(
                startId = 2400,
                categoryId = 102,
                colors = Colors.colors(
                    "Акация лорка",
                    "Вяз благородный",
                    "Суар Темный",
                    "Снежная Патина",
                ),
                categoryCode = "result/korpusnaya_mebel/kabinet",
                uniqName = "riva/yalta",
                url = "https://riva.ru/catalog/yalta/"
            )
            .download(
                startId = 2800,
                categoryId = 104,
                colors = Colors.colors(
                    "Серый",
                    "Венге металлик",
                    "Клен металлик",
                ),
                categoryCode = "result/korpusnaya_mebel/reseptions",
                uniqName = "riva/reseptions-riva",
                url = "https://riva.ru/catalog/riva1/"
            )
    }
}