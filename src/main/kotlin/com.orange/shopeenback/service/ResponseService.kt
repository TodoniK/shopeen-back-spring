package com.orange.shopeenback.service

import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import com.orange.shopeenback.dto.ResponseDTO
import com.orange.shopeenback.entity.Bilan
import com.orange.shopeenback.entity.Response
import com.orange.shopeenback.repository.ResponseRepository

@Service
class ResponsesService(private val responseRepository: ResponseRepository) {

    fun getResponses(): List<Response> {
        return responseRepository.findAll().map { it.toResponseEntity() }
    }

     fun getResponseById(idQuestion: String): Response? {
        val optionalResponses = responseRepository.findById(idQuestion)
        return if( optionalResponses.isPresent ) {
            optionalResponses.get().toResponseEntity()
        } else {
            null
        }
    }

     fun createResponse(response: Response): Response? {
        return if (response.idQuestion != "Default question id") {
            responseRepository.save(response.toResponseDTO()).toResponseEntity()
        } else {
            null
        }
    }

    fun deleteResponse(idQuestion: String): Boolean {
        val exists = responseRepository.existsById(idQuestion)

        return if (exists) {
            responseRepository.deleteById(idQuestion);
            true
        }else{
            false;
        }
    }

     fun getBilan(): Bilan? {
        return if(this.getResponses().isNotEmpty()){
            Bilan().calculerBilan(this.getResponses())
        }else {
            null
        }
    }
}

fun Response.toResponseDTO() = ResponseDTO(
    idQuestion = this.idQuestion,
    userResponse = this.userResponse,
    consoMoy = this.consoMoy
)

fun ResponseDTO.toResponseEntity() = Response(
    idQuestion = this.idQuestion,
    userResponse = this.userResponse,
    consoMoy = this.consoMoy
)