package com.orange.shopeenback.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import com.orange.shopeenback.dto.ResponseDTO

@Repository
interface ResponseRepository: MongoRepository<ResponseDTO, String>