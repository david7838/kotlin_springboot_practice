package com.example.demo.controller

import com.example.demo.model.Bank
import com.example.demo.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/banks")
class BankController(private  val bankService: BankService) {

    @ExceptionHandler(NoSuchElementException::class )
    fun handleNotFound(e: NoSuchElementException):ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(java.lang.IllegalArgumentException::class )
    fun handleNotFound(e: java.lang.IllegalArgumentException):ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getBanks() = bankService.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = bankService.getBank(accountNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postBank(@RequestBody bank: Bank): Bank {
        return bankService.postBank(bank)
    }

    @PatchMapping
    fun patchBank(@RequestBody bank: Bank): Bank {
        return bankService.patchBank(bank)
    }
}