package tech.shopeenapi.dto

import org.springframework.data.annotation.Id
import org.springframework.stereotype.Component

@Component
data class ResponseDTO(
    @Id
    val idQuestion: String = "Default question id",
    val userResponse: Int = 0,
)
