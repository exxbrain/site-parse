package model

import org.asynchttpclient.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

data class HttpFile (
    private val url: String
) {
    fun download(fileName: String) {
        val file = File(fileName)
        if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
            throw Exception("AAA")
        }
        file.createNewFile()
        val stream = FileOutputStream(file)
        Dsl.asyncHttpClient().prepareGet(url).execute(object: AsyncCompletionHandler<FileOutputStream>() {
            override fun onBodyPartReceived(content: HttpResponseBodyPart?): AsyncHandler.State {
                stream.channel.write(content?.bodyByteBuffer)
                return AsyncHandler.State.CONTINUE
            }
            override fun onCompleted(response: Response?): FileOutputStream {
                return stream
            }
        })
    }
}