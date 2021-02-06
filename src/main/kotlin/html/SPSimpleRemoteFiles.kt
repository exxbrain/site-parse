import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.File

class SPSimpleRemoteFiles(private val remoteFiles: List<SPRemoteFile>) : SPRemoteFiles {

    override suspend fun download(folderPath: String) = coroutineScope {
        val folder = File(folderPath)
        if (folder.exists()) {
            folder.deleteRecursively()
        }
        mkdir(folderPath)
        val batchSize = 20
        val chunks = Math.floor(remoteFiles.size.toDouble() / batchSize).toInt()
        for (i in 0..chunks) {
            val startIndex = i * batchSize
            var endIndex = startIndex + batchSize
            endIndex = if (endIndex > remoteFiles.lastIndex) remoteFiles.lastIndex + 1 else endIndex
            val deferreds = remoteFiles.subList(startIndex, endIndex).map {
                async(Dispatchers.IO) {
                    println(it)
                    it.download(folderPath)
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