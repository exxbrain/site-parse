package proteh

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
internal class ProtehPages {

    @Test
    fun saveYasenShimo() {
        //ArgoPages.yasenShimo.download()
        ArgoPages.yasenShimo.save()
    }

    @Test
    fun saveGarbo() {
        //ArgoPages.garbo.download()
        ArgoPages.garbo.save()
    }

    @Test
    fun saveWhite() {
        // ArgoPages.white.download()
        ArgoPages.white.save()
    }
}