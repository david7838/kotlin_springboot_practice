package com.example.demo.service

import com.example.demo.model.BeerDto
import org.springframework.stereotype.Service
import java.util.UUID

@Service
interface BeerService {
    fun getBeerById(beerId: UUID): BeerDto
}