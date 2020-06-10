package proteh

import model.Page
import org.openqa.selenium.By

class Pages {
    companion object {
        fun pages(
            code: String,
            url: String,
            categoryId: Long,
            parentCategory: Category,
            startId: Long,
            colors: List<Color> = listOf()
        ): List<Page> {
            val color = colors[0];
            val lastColors = colors.subList(1, colors.size)
            val first = Page(
                url = url,
                code = color.code,
                filePath = "./src/test/resources/${parentCategory.folder}/$code-${color.code}.html",
                targetFolder = "./result/${parentCategory.folder}/$code/${color.code}",
                products = ProtehProducts(
                    startId = startId,
                    categoryId = categoryId,
                    imageFolder = "catalog/${parentCategory.folder}/$code/${color.code}",
                    options = linkedMapOf("Цвет" to colors.map { it.name }),
                    additionalImageFolders = colors.subList(1, colors.size)
                        .map { "catalog/${parentCategory.folder}/$code/${it.code}" }
                )
            )

            val res = mutableListOf(first)
            res.addAll(lastColors.mapIndexed { index, color -> Page(
                url = url,
                code = color.code,
                filePath = "./src/test/resources/${parentCategory.folder}/$code-${color.code}.html",
                targetFolder = "./result/${parentCategory.folder}/$code/${color.code}",
                products = ProtehProducts(
                    startId = 0,
                    categoryId = categoryId,
                    imageFolder = "catalog/${parentCategory.folder}/$code/${color.code}"
                ),
                beforeDownload = {
                    val element = it.findElements(By.cssSelector("#colors div"))[index + 1]
                    element.click()
                }
            ) })

            return res
        }
    }
}