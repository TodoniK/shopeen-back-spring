package tech.shopeenapi.dto

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

@Document(collection = "application")
@TypeAlias("Application")
data class ApplicationDTO(
    @Id
    val idInterne: ObjectId = ObjectId(),
    val idExtName: String = "Default application name",
    val bilanEuro: Int = 0,
    val bilanCO2: Int = 0,
    val bilanEnergy: Int = 0,
    val measurementDate: String = LocalDate.now().toString()
)
