package tech.shopeenapi.service.unit

import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import tech.shopeenapi.dto.ResponseDTO
import tech.shopeenapi.entity.Response
import tech.shopeenapi.repository.ResponseRepository
import tech.shopeenapi.service.ResponsesServiceImpl
import tech.shopeenapi.service.toResponseEntity
import java.util.*

internal class ResponsesServiceImplShould{
    private val responseRepository = mockkClass(ResponseRepository::class)

    private val responsesServiceImpl = ResponsesServiceImpl(responseRepository)

    @Test
    internal fun `map ResponseDTO to ResponseEntity`() {
        val responseDTO = ResponseDTO(idQuestion = "nbPC", userResponse = 16)

        val responseEntity = responseDTO.toResponseEntity()

        assertEquals("nbPC",responseEntity.idQuestion)
        assertEquals(16,responseEntity.userResponse)

    }

    @Nested
    @DisplayName("getResponses")
    inner class GetResponses {
        @Test
        internal fun `return an empty map`() {
            every { responseRepository.findAll() } returns listOf()

            val responses = responsesServiceImpl.getResponses()

            assertTrue(responses.isEmpty())
        }

        @Test
        internal fun `return all responses`() {
            val response1 = ResponseDTO("nbPC",16)
            val response2 = ResponseDTO("nbMoniteurs",45)
            every { responseRepository.findAll() } returns listOf(response2,response1)

            val responses = responsesServiceImpl.getResponses()

            assertTrue(responses.isNotEmpty())
            assertEquals(45,responses[0].userResponse)
        }
    }

    @Nested
    @DisplayName("getResponseById")
    inner class GetResponseById {
        @Test
        internal fun `return null if id doesn't exist in repository`() {
            every { responseRepository.findById((any())) } returns Optional.empty()

            val response = responsesServiceImpl.getResponseById("nbPC")

            assertNull(response)
        }

        @Test
        internal fun `return ResponseEntity with existing user response`() {
            val responseDTO = ResponseDTO(idQuestion = "nbPC", userResponse = 16)
            every { responseRepository.findById((any())) } returns Optional.of(responseDTO)

            val response = responsesServiceImpl.getResponseById("nbPC")

            assertEquals("nbPC", response?.idQuestion)
            assertEquals(16, response?.userResponse)
        }
    }

    @Nested
    @DisplayName("pushResponse")
    inner class PushResponse {
        @Test
        internal fun `return an empty repo`() {
            val response = Response(idQuestion = "Default question id", userResponse = 16)

            val createReturn = responsesServiceImpl.createResponse(response)

            assertNull(createReturn)
            verify(exactly = 0) { responseRepository.save(any()) }
        }

        @Test
        internal fun `return the response pushed`() {
            val response = Response(idQuestion = "nbPC", userResponse = 16)
            val responseDTO = ResponseDTO(idQuestion = "nbPC", userResponse = 16)
            every { responseRepository.save((any())) } returns responseDTO

            val responseCreated = responsesServiceImpl.createResponse(response)

            verify(exactly = 1) { responseRepository.save(any()) }
            assertNotNull(responseCreated)
            assertEquals("nbPC", responseCreated?.idQuestion)
        }
    }

    @Nested
    @DisplayName("updateResponse")
    inner class UpdateResponse {
        @Test
        internal fun `return the response updated`() {
            val response = Response(idQuestion = "nbPC", userResponse = 16)
            val responseDTO = ResponseDTO(idQuestion = "nbPC", userResponse = 16)
            every { responseRepository.save((any())) } returns responseDTO
            every { responseRepository.existsById((any())) } returns true

            val responseUpdated = responsesServiceImpl.updateResponse(response)

            verify(exactly = 1) { responseRepository.existsById(any()) }
            assertNotNull(responseUpdated)
            assertEquals("nbPC", responseUpdated?.idQuestion)
        }
    }

    /*@Nested
    @DisplayName("deleteResponse")
    inner class DeleteResponse {
        @Test
        internal fun `call the delete function of repo`(){
            every { responseRepository.existsById((any())) } returns true

            responsesServiceImpl.deleteResponse("nbPC")

            verify(exactly = 1){ responseRepository.deleteById(any()) }
        }
    }*/

    @Nested
    @DisplayName("getBilan")
    inner class GetBilan {
        @Test
        internal fun `return null because no responses`(){
            every { responseRepository.findAll() } returns listOf()

            val bilan = responsesServiceImpl.getBilan()

            verify(exactly = 1){ responseRepository.findAll() }
            assertNull(bilan)
        }

        @Test
        internal fun `return a bilan calculated by multiples responses sent`(){
            val response1 = ResponseDTO("nbPC",16)
            val response2 = ResponseDTO("nbMoniteurs",45)
            every { responseRepository.findAll() } returns listOf(response1,response2)

            val bilan = responsesServiceImpl.getBilan()

            assertNotNull(bilan)
            assertEquals(71.37,bilan?.bilanEuro)
        }
    }

    @Nested
    @DisplayName("getBilanById")
    inner class GetBilanById {
        @Test
        internal fun `return null if bilan doesn't exist`() {
            every { responseRepository.findById((any())) } returns Optional.empty()

            val bilan = responsesServiceImpl.getBilanById("nbPC")

            assertNull(bilan)
        }

        @Test
        internal fun `return a bilan calculated by searched response`() {
            val response1 = ResponseDTO("nbPC",16)
            every { responseRepository.findById((any())) } returns Optional.of(response1)

            val bilan = responsesServiceImpl.getBilanById("nbPC")

            assertNotNull(bilan)
            assertEquals(18.72,bilan?.bilanEuro)
        }
    }
}