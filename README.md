# Web page parser for Open Cart
Generates xls files for import to Open Cart.

Example:

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
