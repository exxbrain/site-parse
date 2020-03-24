package catalog

import com.marcellogalhardo.fixture.Fixture
import com.marcellogalhardo.fixture.next
import model.ProductCollection
import http.Download
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ExcelCollectionTest {

    @MockK
    lateinit var collection: ProductCollection

    @MockK
    lateinit var download: Download

    private val fixture = Fixture()

    @Test
    fun save() {
        every { collection.products } returns listOf(
            fixture.next(),
            fixture.next()
        )
        ExcelCollection(collection).save("argo", download)
    }
}