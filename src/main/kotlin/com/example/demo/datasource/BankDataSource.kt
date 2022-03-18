package com.example.demo.datasource

import com.example.demo.model.Bank

interface BankDataSource {

    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun saveBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
}