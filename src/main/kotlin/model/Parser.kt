package model

import org.jsoup.nodes.Document

interface Parser {
    fun parse(document: Document, collection: ProductCollection): List<Product>
}