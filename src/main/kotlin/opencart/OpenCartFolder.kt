package opencart

import excel.ExcelWb
import excel.WithData
import kotlinx.coroutines.*
import model.RemoteFile
import org.asynchttpclient.Response
import java.io.File
import java.io.FileOutputStream
import java.util.*

class OpenCartFolder(folder: String) {
    private val folder: String = folder.stringByRemovingLastChar('/')
    init {
        val file = File(folder)
        if (!file.exists() && !file.mkdirs()) {
            throw Exception("AAA")
        }
    }

    suspend fun save(images: List<RemoteFile>, imgFolder: String) = coroutineScope {
        mkdir(imgFolder)
        images.size
        val batchSize = 20
        val chunks = Math.floor(images.size.toDouble() / batchSize).toInt()
        for (i in 0..chunks) {
            val startIndex = i * batchSize
            var endIndex = startIndex + batchSize
            endIndex = if (endIndex > images.lastIndex) images.lastIndex + 1 else endIndex
            val deferreds = images.subList(startIndex, endIndex).map {
                async(Dispatchers.IO) {
                    println(it.fileName)
                    it.download("${folder}/${imgFolder.stringByRemovingLastChar('/')}")
                }
            }
            deferreds.awaitAll()
            println("$i. --------")
        }
    }

    fun save(text: String, fileName: String) {
        val file = File("./${folder}/${fileName}")
        val stream = FileOutputStream(file)
        stream.use { s -> s.write(text.toByteArray()) }
    }

    fun save(data: LinkedHashMap<String, List<WithData>>, fileName: String) {
        val wb = ExcelWb(data)
        wb.save("${folder}/${fileName}")
    }

    suspend fun save(image: RemoteFile, imgFolder: String) {
        mkdir(imgFolder)
        save(listOf(image), imgFolder)
    }

    fun clear() {
        val file = File(folder)
        file.deleteRecursively()
        if (!file.exists() && !file.mkdirs()) {
            throw Exception("AAA")
        }
    }

    fun mkdir(dir: String) {
        val file = File("$folder/$dir")
        if (!file.exists() && !file.mkdirs()) {
            throw Exception("AAA")
        }
    }
}

fun String.stringByRemovingLastChar(char: Char): String {
    if (this.last() == char) {
        return this.substring(0, this.length - 1)
    }
    return this
}
