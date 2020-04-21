package model

import files.RemoteFile

interface Header {
    fun getImages(filePath: String): List<RemoteFile>
}