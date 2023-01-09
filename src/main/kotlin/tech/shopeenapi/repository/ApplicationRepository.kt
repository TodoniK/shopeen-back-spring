package tech.shopeenapi.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import tech.shopeenapi.dto.ApplicationDTO

@Repository
interface ApplicationRepository: MongoRepository<ApplicationDTO, String>