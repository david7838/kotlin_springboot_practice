package com.example.demo.datasource.mock

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {

    val banks = mutableListOf<Bank>(
        Bank("1234", 3.14, 17),
        Bank("5678", 3.33, 118),
        Bank("1345", 3.42, 19),
    )

    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull { it -> it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("could not find a bank with accountNumber : $accountNumber")
        //?: => 不為null則回傳左邊的值, null則回傳右邊
    }

    override fun saveBank(bank: Bank): Bank {
        if(banks.any { it.accountNumber == bank.accountNumber }) {
            throw java.lang.IllegalArgumentException("the bank account is already exist => bankAccount : ${bank.accountNumber}")
        }
        this.banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("could not find a bank with accountNumber : ${bank.accountNumber}")

        banks.remove(currentBank)

        banks.add(bank)

        return bank
    }

    override fun retrieveBanks(): Collection<Bank> = banks
}