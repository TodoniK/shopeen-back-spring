package tech.shopeenapi.entity

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Bilan of power, CO2 and money consumption, based on user responses")
class Bilan(
    private val userResponse: Int = 0,
    private val coefEuro: Double = 1.17,
    private val coefKwh: Double = 2.36,
    private val coefKgeqCO2: Double = 0.69
) {
    @field:Schema(
        description = "Private bilan value, represents money consumed by all equipment",
        example = "327.25",
        type = "double",
    )
    var bilanEuro = userResponse * coefEuro
    @field:Schema(
        description = "Private bilan value, represents Kwh consumed by all equipment",
        example = "327.25",
        type = "double",
    )
    var bilanKwh = userResponse * coefKwh

    @field:Schema(
        description = "Private bilan value, represents CO2 consumed by all equipment",
        example = "327.25",
        type = "double",
    )
    var bilanKgeqCO2 = userResponse * coefKgeqCO2

    fun calculerBilan(tabResponses : List<Response>) : Bilan {

        for(response in tabResponses){
            this.bilanKwh = this.bilanKwh + response.userResponse * coefKwh
            this.bilanEuro = this.bilanEuro + response.userResponse * coefEuro
            this.bilanKgeqCO2 = this.bilanKgeqCO2 + response.userResponse * coefKgeqCO2
        }

        return this
    }
}