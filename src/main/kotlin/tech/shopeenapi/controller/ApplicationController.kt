package tech.shopeenapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.shopeenapi.entity.Application
import tech.shopeenapi.service.ApplicationService

@RestController
@CrossOrigin
@RequestMapping("/api")
class ApplicationControllerController(private val applicationService: ApplicationService) {
    
    @Operation(summary = "Get all bilan of all applications", description = "Returns multiples dated bilans of Orange developped applications if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Apps historics tab is empty"),
        ]
    )
    @GetMapping("/appli")
    fun getApplicationsHistoric(): ResponseEntity<List<Application>> =
        ResponseEntity.ok(applicationService.getApplicationsHistorics())

    @Operation(summary = "Post a dated historic bilan of an Orange developped application", description = "Returns 200 if added correctly")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Bad inputs"),
        ]
    )
    @PostMapping("/appli")
    fun postAppBilan(@RequestBody app: Application): ResponseEntity<Application> =
        ResponseEntity.ok(applicationService.createApplicationHistoric(app))

}