package tech.shopeenapi.service

import tech.shopeenapi.entity.Bilan
import tech.shopeenapi.entity.Response

interface ResponsesService {
    fun createResponse(response: Response): Response?
    fun getResponses(): List<Response>
    fun getResponseById(idQuestion: String): Response?
    fun deleteResponse(idQuestion: String)
    fun getBilanById(idQuestion: String) : Bilan?
    fun getBilan() : Bilan?
}