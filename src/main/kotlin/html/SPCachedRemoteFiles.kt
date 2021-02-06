import java.io.File

class SPCachedRemoteFiles(private val remoteFiles: SPSimpleRemoteFiles) : SPRemoteFiles {
    override suspend fun download(folderPath: String) {
        val folder = File(folderPath)
        if (folder.exists()) {
            return
        }
        remoteFiles.download(folderPath)
    }
}