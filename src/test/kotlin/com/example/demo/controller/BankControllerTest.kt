package com.example.demo.controller

import com.example.demo.model.Bank
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
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
){

    val baseUrl = "/api/v1/banks"

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`(){
            //given


            //when
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234")}
                }
            //then
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return one bank with given account number`(){
            //given
            val accountNumber = 1234

            //when
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust"){ value( 3.14 )}
                    jsonPath("$.transactionFee"){ value( 17 )}
                }
            //then

        }

        @Test
        fun `should return NOT FOUND if the account not exist`(){
            //given
            val accountNumber = "not exist"

            //when
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
            //then
        }


    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {
    
        @Test
        fun `should add the new bank`(){
            //given
            val newBank = Bank(
                "myAccount",
                2.0,
                20
            )
            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            performPost.andDo { print() }
                       .andExpect {
                           status { isCreated() }
                           content { contentType(MediaType.APPLICATION_JSON) }
                           jsonPath("$.accountNumber") { value("myAccount")}
                           jsonPath("$.trust") { value("2.0")}
                           jsonPath("$.transactionFee") { value("20")}
                       }

        }

        @Test
        fun `should return Bad Request when bank account already exist`(){
            //given
            val existBankAccount = Bank(
                "1234",
                2.0,
                20
            )

            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(existBankAccount)
            }
            //then
            performPost.andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }
    
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchBank {
    
        @Test
        fun `should update an existing bank`(){
            //given
            val updateBank = Bank(
                "1234",
                3.0,
                30
            )
            
            //when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBank)
            }

            //then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { json(objectMapper.writeValueAsString(updateBank)) }
                }

            //then check again
            mockMvc.get("$baseUrl/${updateBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updateBank)) } }
        }   
    }

    @Test
    fun `should return NOT FOUND when bank account not existing`(){
        //given
        val notExistingBankAccount = Bank(
            "not_exist",
            1.0,
            10
        )

        //when
        val patch = mockMvc.patch(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(notExistingBankAccount)
        }
        //then
        patch.andDo { print() }
            .andExpect {
                content {
                    status { isNotFound() }
                }
            }
    }

}