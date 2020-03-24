import org.asynchttpclient.*


val client = Dsl.asyncHttpClient()



fun main(args: Array<String>) {
    Collection1("https://www.proteh.ru/argo/", "argo").save()

    /*
    doc.select(".flexslider .slide").forEach{ x: Element ->
        run {
            println(x)
            val thumbUrl = x.attr("data-thumb")
            val picUrl = x.select("img").attr("src")
            val picture = dto.Picture("${url}/${picUrl}", "${url}/${thumbUrl}")
            picture.save("test")
        }
    }
    */
}

val String.extension: String
    get() = this.substringAfterLast('.', "")