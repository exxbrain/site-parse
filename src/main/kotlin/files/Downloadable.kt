package files

interface Downloadable {
    suspend fun download(folder: String)
}
