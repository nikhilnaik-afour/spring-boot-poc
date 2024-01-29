package com.poc.dellnxppoc.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.poc.dellnxppoc.db.dto.ClientMongoDTO;

@Repository
public interface ClientMongoRepository extends MongoRepository<ClientMongoDTO, String> {

}