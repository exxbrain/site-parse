package model

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import org.asynchttpclient.AsyncCompletionHandler
import org.asynchttpclient.AsyncHandler
import org.asynchttpclient.Dsl.asyncHttpClient
import org.asynchttpclient.HttpResponseBodyPart
import org.asynchttpclient.Response
import java.io.File
import java.io.FileOutputStream
import java.util.*


data class RemoteFile (
    val url: String,
    private var fileNameWithoutExtension: String = "${UUID.randomUUID()}"
): Downloadable {
    val fileName = "$fileNameWithoutExtension.${url.extension}"


    override suspend fun download(folder: String) = suspendCancellableCoroutine { continuation: CancellableContinuation<Response> ->
        val file = File("${folder}/${fileName}")
        file.createNewFile()
        val stream = FileOutputStream(file)
        asyncHttpClient().use {
            val response = it.prepareGet(url)
                .execute(object : AsyncCompletionHandler<FileOutputStream>() {
                    @Throws(java.lang.Exception::class)
                    override fun onBodyPartReceived(bodyPart: HttpResponseBodyPart): AsyncHandler.State {
                        stream.channel.write(bodyPart.bodyByteBuffer)
                        return AsyncHandler.State.CONTINUE
                    }

                    override fun onThrowable(t: Throwable) {
                        continuation.resumeWith(Result.failure(t))
                    }

                    @Throws(java.lang.Exception::class)
                    override fun onCompleted(response: Response): FileOutputStream? {
                        stream.close()
                        continuation.resumeWith(Result.success(response))
                        return stream
                    }
                })
                .toCompletableFuture()
            response.join()
        }
    }
}

val String.extension: String
    get() = this.substringAfterLast('.', "")
