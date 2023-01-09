package tech.shopeenapi.dto

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


@Document(collection = "application")
@TypeAlias("Application")
data class ApplicationDTO(
    @Id // ObjectId
    val appName: String = "Default application name",
    val bilanEuro: Int = 0,
    val bilanCO2: Int = 0,
    val bilanEnergy: Int = 0,
    val measurementDate: Date = Date()
)
