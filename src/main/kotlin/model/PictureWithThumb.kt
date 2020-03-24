package model

class PictureWithThumb(private val picture: HttpFile, private val thumb: HttpFile) {
    fun download() {
        picture.download()
        thumb.download()
    }
}
