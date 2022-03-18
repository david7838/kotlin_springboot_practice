package com.example.demo.datasource

import com.example.demo.model.Product

interface ProductDataSource {
    fun getProducts(): Collection<Product>
}