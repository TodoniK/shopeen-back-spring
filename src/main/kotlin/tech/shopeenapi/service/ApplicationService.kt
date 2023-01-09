package tech.shopeenapi.service

import org.springframework.stereotype.Service
import tech.shopeenapi.dto.ApplicationDTO
import tech.shopeenapi.entity.Application
import tech.shopeenapi.repository.ApplicationRepository
import java.util.*


@Service
class ApplicationService(private val applicationRepository: ApplicationRepository) {

    fun getApplicationsHistorics(): List<Application> {
        return applicationRepository.findAll().map { it.toAppEntity() }
    }

     fun createApplicationHistoric(application: Application): Application? {
        return if (application.appName != "Default app name") {
            applicationRepository.save(application.toAppDTO()).toAppEntity()
        } else {
            null
        }
    }

}

fun Application.toAppDTO() = ApplicationDTO(
    appName = this.appName,
    bilanEuro = this.bilanEuro,
    bilanCO2 = this.bilanCO2,
    bilanEnergy = this.bilanEnergy,
    measurementDate = Date()
)

fun ApplicationDTO.toAppEntity() = Application(
    appName = this.appName,
    bilanEuro = this.bilanEuro,
    bilanCO2 = this.bilanCO2,
    bilanEnergy = this.bilanEnergy,
)