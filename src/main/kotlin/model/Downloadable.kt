package model

import org.asynchttpclient.Response

interface Downloadable {
    suspend fun download(folder: String): Response
}
