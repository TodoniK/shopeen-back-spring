package com.orange.shopeenback.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.orange.shopeenback.entity.Bilan
import com.orange.shopeenback.entity.Response
import com.orange.shopeenback.service.ResponsesService

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
    fun getAllResponses(): ResponseEntity<List<Response>> =
        ResponseEntity.ok(responsesService.getResponses())

    @Operation(summary = "Get a specific response by id", description = "Returns a unique response")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Response requested doesn't exists"),
        ]
    )
    @GetMapping("/responses/{idQuestion}")
    fun getResponseById(@PathVariable idQuestion: String) =
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
    fun deleteResponseById(@PathVariable idQuestion: String): ResponseEntity<Unit> {
        val retour = responsesService.deleteResponse(idQuestion)
        return if(retour){
            ResponseEntity(HttpStatus.OK)
        }else{
            ResponseEntity(HttpStatus.CONFLICT)
        }
    }


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