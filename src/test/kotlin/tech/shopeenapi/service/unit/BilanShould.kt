package tech.shopeenapi.service.unit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import tech.shopeenapi.entity.Bilan
import tech.shopeenapi.entity.Response

internal class BilanShould{

    @Test
    fun `bilan equals to 0`(){
        val unBilan = Bilan();
        assertThat(unBilan.bilanEuro).isEqualTo(0.0)
    }

    @Test
    fun `return an integer superior than direct response`(){
        val userResponse = 78
        val unBilan = Bilan(userResponse);
        assertThat(unBilan.bilanEuro).isGreaterThan(userResponse.toDouble())
    }

    @Test
    fun `bilan not empty after getting multiple results`(){

        val userResponses = listOf<Response>(
            Response("nbPC",16),
            Response("nbMoniteurs",31),
            Response("nbServeurs",197)
        )

        val unBilan = Bilan().calculerBilan(userResponses);

        assertThat(unBilan.bilanEuro).isNotNaN()
    }

    @Test
    fun `return bilan for multiples results`(){

        val userResponses = listOf<Response>(
            Response("nbPC",16),
            Response("nbMoniteurs",31),
            Response("nbServeurs",197)
        )

        val unBilan = Bilan().calculerBilan(userResponses);

        assertThat(unBilan.bilanEuro).isEqualTo(285.47999999999996)
    }

}