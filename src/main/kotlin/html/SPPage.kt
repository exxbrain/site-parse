import org.jsoup.nodes.Document

interface SPPage {
    fun onLoad(onLoad: (interactor: SPPageInteractor) -> Unit): SPPage
    fun save(filePath: String): Document
}