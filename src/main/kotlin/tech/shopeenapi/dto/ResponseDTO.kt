package tech.shopeenapi.dto

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.stereotype.Component

@Document(collection = "response")
@TypeAlias("Response")
data class ResponseDTO(
    @Id
    val idQuestion: String = "Default question id",
    val userResponse: Int = 0,
    val consoMoy: Double = 0.0
)
