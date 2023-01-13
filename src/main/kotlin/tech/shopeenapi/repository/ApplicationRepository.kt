package tech.shopeenapi.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import tech.shopeenapi.dto.ApplicationDTO

@Repository
interface ApplicationRepository: MongoRepository<ApplicationDTO, String>{
    @Query("{ 'idExtName' : ?0 }")
    fun findApplicationDTOByIdExtName(name: String): List<ApplicationDTO>
}