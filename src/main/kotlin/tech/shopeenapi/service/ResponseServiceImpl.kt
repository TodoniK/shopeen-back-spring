package tech.shopeenapi.service

import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import tech.shopeenapi.dto.ResponseDTO
import tech.shopeenapi.entity.Bilan
import tech.shopeenapi.entity.Response
import tech.shopeenapi.repository.ResponseRepository

@Service
class ResponsesServiceImpl(private val responseRepository: ResponseRepository) : ResponsesService {

    override fun getResponses(): List<Response> {
        return responseRepository.findAll().map { it.toResponseEntity() }
    }

    override fun getResponseById(idQuestion: String): Response? {
        val optionalResponses = responseRepository.findById(idQuestion)
        return if( optionalResponses.isPresent ) {
            optionalResponses.get().toResponseEntity()
        } else {
            null
        }
    }

    override fun createResponse(response: Response): Response? {
        return if (response.idQuestion != "Default question id") {
            responseRepository.save(response.toResponseDTO()).toResponseEntity()
        } else {
            null
        }
    }

    override fun updateResponse(response: Response): Response? {
        val exists = responseRepository.existsById(response.toResponseDTO().idQuestion)
        return if(exists){
            responseRepository.save(response.toResponseDTO()).toResponseEntity()
        } else {
            null
        }
    }

    @Throws(HttpStatusCodeException::class)
    override fun deleteResponse(idQuestion: String) {
        val exists = responseRepository.existsById(idQuestion)

        if (exists) {
            responseRepository.deleteById(idQuestion)
        }
    }
    override fun getBilan(): Bilan? {
        return if(this.getResponses().isNotEmpty()){
            Bilan().calculerBilan(this.getResponses())
        }else {
            null
        }
    }
    override fun getBilanById(idQuestion: String): Bilan? {
        val response = this.getResponseById(idQuestion)
        return if( response != null ) {
            Bilan(response.userResponse)
        } else {
            null
        }
    }
}

fun Response.toResponseDTO() = ResponseDTO(
    idQuestion = this.idQuestion,
    userResponse = this.userResponse
)

fun ResponseDTO.toResponseEntity() = Response(
    idQuestion = this.idQuestion,
    userResponse = this.userResponse
)