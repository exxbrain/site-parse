package model

import org.asynchttpclient.Dsl.asyncHttpClient
import org.asynchttpclient.Response
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

data class RemoteFile (
    val url: String,
    private var fileNameWithoutExtension: String = "${UUID.randomUUID()}"
){
    val fileName = "$fileNameWithoutExtension.${url.extension}"


    suspend fun download(folder: String) = suspendCoroutine { continuation: Continuation<Response> ->
        val file = File("${folder}/${fileName}")
        if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
            throw Exception("AAA")
        }
        file.createNewFile()

        asyncHttpClient().use {
            val resonse = it.prepareGet(url)
                .execute()
                .toCompletableFuture()
                .exceptionally {
                    continuation.resumeWith(Result.failure(it))
                    return@exceptionally null
                }
                .thenApply {
                    Files.copy(it.responseBodyAsStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING)
                    println(file.toPath())
                    continuation.resumeWith(Result.success(it))
                    return@thenApply it
                }
            resonse.join()
        }
    }
}

val String.extension: String
    get() = this.substringAfterLast('.', "")
