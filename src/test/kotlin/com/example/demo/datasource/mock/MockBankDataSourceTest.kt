package com.example.demo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest{

    private val mockDataSource = MockBankDataSource()
    @Test
    fun `should provide a collection of banks`(){
        //given

        
        //when
        val banks = mockDataSource.retrieveBanks()

        //then
        assertThat(banks).size().isGreaterThanOrEqualTo(3)

    }
    
    @Test
    fun `should provide some mock data`(){

        //when
        val banks = mockDataSource.retrieveBanks()
    banks.forEach { it -> println(it) }

        //then
//        assertThat(banks).allSatisfy { it.accountNumber.isNotBlank() }
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }

        assertThat(banks).allMatch { it.trust != 0.0 }

        assertThat(banks).allMatch { it.transactionFee != 0 }
    }
}