package model

interface ProductCollection {
    val headerText: String
    val gallery: List<PictureWithThumb>
    val products: List<Product>
}
