package tech.shopeenapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.shopeenapi.entity.Bilan
import tech.shopeenapi.entity.Response
import tech.shopeenapi.service.ResponsesService

@RestController
@CrossOrigin
@RequestMapping("/api")
class ResponsesController(private val responsesService: ResponsesService) {

    @Operation(summary = "Get all the responses sent by the user", description = "Returns all entities if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Responses tab is empty"),
        ]
    )
    @GetMapping("/responses")
    fun getResponses(): ResponseEntity<List<Response>> =
        ResponseEntity.ok(responsesService.getResponses())

    @Operation(summary = "Get a specific response by id", description = "Returns a unique response")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Response requested doesn't exists"),
        ]
    )
    @GetMapping("/responses/{idQuestion}")
    fun getResponse(@PathVariable idQuestion: String) =
        ResponseEntity.ok(responsesService.getResponseById(idQuestion))

    @Operation(summary = "Send to DB a response entered by the user", description = "Returns 200 if added correctly")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Infos entered doesn't match with DB schema"),
        ]
    )
    @PostMapping("/responses")
    fun createResponse(@RequestBody response: Response): ResponseEntity<Response> =
        ResponseEntity.ok(responsesService.createResponse(response))


    @Operation(summary = "Delete a response by id", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Response doesn't exists"),
        ]
    )
    @DeleteMapping("/responses/{idQuestion}")
    fun deleteResponse(@PathVariable idQuestion: String): ResponseEntity<Unit> =
        ResponseEntity(responsesService.deleteResponse(idQuestion), HttpStatus.OK)

    @Operation(summary = "Get final power bilan with all responses", description = "Returns a bilan composed of 3 values if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Responses tab is empty"),
        ]
    )
    @GetMapping("/bilan")
    fun getBilan(): ResponseEntity<Bilan> =
        ResponseEntity.ok(responsesService.getBilan())

}