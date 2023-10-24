package com.orange.shopeenback.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import com.orange.shopeenback.dto.ApplicationDTO

@Repository
interface ApplicationRepository: MongoRepository<ApplicationDTO, ObjectId>{
    @Query("{ 'idExtName' : ?0 }")
    fun findApplicationDTOByIdExtName(name: String): List<ApplicationDTO>
}