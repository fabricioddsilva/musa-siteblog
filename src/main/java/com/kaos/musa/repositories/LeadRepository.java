package com.kaos.musa.repositories;

import com.kaos.musa.entities.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends MongoRepository<Lead, String> {
}
