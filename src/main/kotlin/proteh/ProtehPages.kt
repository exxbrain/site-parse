package proteh

import model.Page

enum class ProtehPages(val pages: List<Page>) {
    argentum(Pages.pages(
        code = "argentum",
        url = "https://www.proteh.ru/argentum/",
        categoryId = 93,
        parentCategory = Category.operativnya_mebel,
        startId = 800,
        colors = listOf(
            Color("dub-safari", "Дуб сафари"),
            Color("sosna-vinter", "Сосна винтер"),
            Color("listvennica", "Лиственница")
        )
    )),

    avantage(Pages.pages(
        code = "avantage",
        url = "https://www.proteh.ru/avantage/",
        categoryId = 94,
        parentCategory = Category.operativnya_mebel,
        startId = 1000,
        colors = listOf(
            Color("dub-shamoni", "Дуб шамони"),
            Color("milanski-oreh", "Миланский орех")
        )
    )),

    prioritet(Pages.pages(
        code = "prioritet",
        url = "https://www.proteh.ru/prioritet/",
        categoryId = 95,
        parentCategory = Category.kabinetnaya_mebel,
        startId = 1100,
        colors = listOf(
            Color("kronberg", "Кронберг"),
            Color("garbo", "Гарбо"),
            Color("dub-venge", "Дуб венге")

        )
    )),

    matrica(Pages.pages(
        code = "matrica",
        url = "https://www.proteh.ru/matrica/",
        categoryId = 96,
        parentCategory = Category.operativnya_mebel,
        startId = 1200,
        colors = listOf(
            Color("yasen-shimo", "Ясень шимо"),
            Color("mali-venge", "Мали венге")

        )
    )),

    kalle(Pages.pages(
        code = "kalle",
        url = "https://www.proteh.ru/kalle/",
        categoryId = 97,
        parentCategory = Category.mebel_dlya_doma,
        startId = 1300,
        colors = listOf(
            Color("oniks-yosemite", "Оникс Yosemite"),
            Color("belyi-engadina", "Белый Engadina")
        )
    )),



}