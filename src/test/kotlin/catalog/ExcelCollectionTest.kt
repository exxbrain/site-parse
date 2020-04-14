package catalog

import model.Collection
import opencart.OpenCartFolder
import com.marcellogalhardo.fixture.Fixture
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ExcelCollectionTest {

    @MockK
    lateinit var openCartFolder: OpenCartFolder

    @MockK
    lateinit var document: Collection


    private val fixture = Fixture()

    @Test
    fun save() {

    }
}