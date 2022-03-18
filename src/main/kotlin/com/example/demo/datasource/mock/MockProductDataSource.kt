package com.example.demo.datasource.mock

import com.example.demo.datasource.ProductDataSource
import com.example.demo.model.Product

class MockProductDataSource: ProductDataSource {

    val products = listOf(
        Product("juice", 100.0),
        Product("book", 200.0),
        Product("computer", 1000.0),
    )

    override fun getProducts(): Collection<Product> = products
}