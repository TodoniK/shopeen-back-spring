package com.orange.shopeenback.service

import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import com.orange.shopeenback.dto.ApplicationDTO
import com.orange.shopeenback.entity.Application
import com.orange.shopeenback.repository.ApplicationRepository
import java.time.LocalDate


@Service
class ApplicationService(private val applicationRepository: ApplicationRepository) {

    fun getApplicationsHistorical(): List<Application> {
        return applicationRepository.findAll().map { it.toAppEntity() }
    }

     fun createApplicationHistorical(application: Application): Application? {
        return if (application.idExtName != "Default app name") {
            applicationRepository.save(application.toAppDTO()).toAppEntity()
        } else {
            null
        }
    }

    fun getApplicationsHistoricalByName(name: String): List<Application> {
        return applicationRepository.findApplicationDTOByIdExtName(name).map { it.toAppEntity() }
    }

}

fun Application.toAppDTO() = ApplicationDTO(
    idInterne = ObjectId(),
    idExtName = this.idExtName,
    bilanEuro = this.bilanEuro,
    bilanCO2 = this.bilanCO2,
    bilanEnergy = this.bilanEnergy,
    measurementDate = LocalDate.now().toString()
)

fun ApplicationDTO.toAppEntity() = Application(
    idExtName = this.idExtName,
    bilanEuro = this.bilanEuro,
    bilanCO2 = this.bilanCO2,
    bilanEnergy = this.bilanEnergy,
    measurementDate = this.measurementDate
)