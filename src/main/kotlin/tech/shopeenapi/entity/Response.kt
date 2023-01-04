package tech.shopeenapi.entity

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Response of a quizz sent by the front, typed by the user")
data class Response(
    @field:Schema(
        description = "Question identifier, used to manage responses",
        example = "nbPC",
        type = "string",
    )
    val idQuestion: String,

    @field:Schema(
        description = "Value sent by the user, corresponding to a number of things",
        example = "16",
        type = "int",
        minimum = "0",
        maximum = "999"
    )
    val userResponse: Int,
)
