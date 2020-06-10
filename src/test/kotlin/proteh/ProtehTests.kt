package proteh

import org.junit.jupiter.api.Test


internal class ProtehTests {

    @Test
    fun download() {
        ProtehPages.kalle.pages.forEach { it.save() }
    }

    @Test
    fun downloadAll() {
        //ArgoPages.garbo.downloadAll()
        AccentPages.downloadAll()
    }

    @Test
    fun savePictures() {
        //ArgoPages.saveAll()
        AccentPages.saveAll()
    }
}