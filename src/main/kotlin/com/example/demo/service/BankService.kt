package com.example.demo.service

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService (private val bankDataSource: BankDataSource){

    fun getBanks(): Collection<Bank> = this.bankDataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank {
        return this.bankDataSource.retrieveBank(accountNumber)
    }

    fun postBank(bank: Bank): Bank {
        return this.bankDataSource.saveBank(bank)
    }

    fun patchBank(bank: Bank): Bank {
        return this.bankDataSource.updateBank(bank)
    }
}