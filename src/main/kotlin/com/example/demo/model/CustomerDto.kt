package com.example.demo.model

import java.util.UUID

data class CustomerDto (
    val UUID: UUID,
    val name: String = "default name"
){
}