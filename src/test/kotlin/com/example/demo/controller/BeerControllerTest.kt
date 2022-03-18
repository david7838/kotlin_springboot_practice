package com.example.demo.controller

import com.example.demo.model.BeerDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BeerControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
){

    val beerBaseUrl = "/api/v1/beer"

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `GetBeer`() {

        @Test
        fun `should return NOT FOUND`(){
            //given


            //when
            mockMvc.get("$beerBaseUrl")
                .andDo { print() }
                .andExpect { status { isNotFound() } }

            //then

        }
        @Test
        fun `should return random beer`(){
            //given
            val randomUUID = UUID.randomUUID()
            val randomBeer = BeerDto(randomUUID)

            //when
            val performGetBeerRequest = mockMvc.get("$beerBaseUrl/$randomUUID"){
                contentType = MediaType.APPLICATION_JSON
            }


            //then
            performGetBeerRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType( MediaType.APPLICATION_JSON )
                        json(objectMapper.writeValueAsString(randomBeer))
                    }
                }
        }

    }

}