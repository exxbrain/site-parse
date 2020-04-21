package model

import files.FileSource

interface Collection : FileSource {
    val products: List<Product>
}