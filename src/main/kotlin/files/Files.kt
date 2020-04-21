package files

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.File

class Files(private val images: List<RemoteFile>) : Downloadable {
    constructor(vararg image: RemoteFile) : this(image.asList())
    constructor(fileSource: FileSource) : this(fileSource.images)


    override suspend fun download(folder: String) = coroutineScope {
        mkdir(folder)
        val images = images
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
                    it.download(folder)
                }
            }
            deferreds.awaitAll()
            println("$i. --------")
        }
    }

    private fun mkdir(dir: String) {
        val file = File(dir)
        if (!file.exists() && !file.mkdirs()) {
            throw Exception("AAA")
        }
    }
}