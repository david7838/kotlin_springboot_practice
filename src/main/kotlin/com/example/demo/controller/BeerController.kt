package com.example.demo.controller

import com.example.demo.model.BeerDto
import com.example.demo.service.BeerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/beer")
class BeerController(@Autowired private val beerService: BeerService) {

    @GetMapping("/{beerId}")
    fun getBeer(@PathVariable beerId: UUID): ResponseEntity<BeerDto>{
        return ResponseEntity(beerService.getBeerById(beerId), HttpStatus.OK)
    }
}