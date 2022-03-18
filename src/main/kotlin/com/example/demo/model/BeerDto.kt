package com.example.demo.model

import java.util.UUID
import java.util.UUID.randomUUID

data class BeerDto(
    val UUID: UUID = randomUUID(),
    val beerName: String = "",
    val beerStyle: String = "",
    val upc: Long = 10L,
){
//    constructor(UUID: UUID): this(UUID, "", "", 1L)
}