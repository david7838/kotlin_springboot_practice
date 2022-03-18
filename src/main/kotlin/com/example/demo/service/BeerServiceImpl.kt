package com.example.demo.service

import com.example.demo.model.BeerDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class BeerServiceImpl : BeerService {
    override fun getBeerById(beerId: UUID): BeerDto {
        return BeerDto(beerId)
    }
}