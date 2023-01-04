package tech.shopeenapi.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import tech.shopeenapi.dto.ResponseDTO

@Repository
interface ResponseRepository: MongoRepository<ResponseDTO, String>