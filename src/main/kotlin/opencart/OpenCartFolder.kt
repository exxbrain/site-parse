package opencart

import excel.ExcelWb
import excel.WithData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.RemoteFile
import okhttp3.internal.wait
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

    fun save(images: List<RemoteFile>, imgFolder: String) {
        val batchSize = 50
        val sublist = images.subList(0, if (images.size < batchSize) images.size else batchSize)
        runBlocking {
            for (image in sublist) {
                launch {
                    println(image.fileName)
                    image.download("${folder}/${imgFolder.stringByRemovingLastChar('/')}")
                }
            }
        }
        if (images.size > batchSize) {
            save(images.subList(batchSize, images.lastIndex), imgFolder)
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

    fun save(image: RemoteFile, imgFolder: String) {
        save(listOf(image), imgFolder)
    }

    fun clear() {
        val file = File(folder)
        file.deleteRecursively()
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
