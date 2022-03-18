package com.example.demo.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MockProductDataSourceTest {

    private val mockProductDataSource = MockProductDataSource()

    @Test
    fun `should provide some not empty data`(){
        //given


        //when
        val products = mockProductDataSource.getProducts()

        //then
        Assertions.assertThat(products).size().isGreaterThanOrEqualTo(3)
    }
}