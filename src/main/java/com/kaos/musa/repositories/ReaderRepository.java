package com.kaos.musa.repositories;

import com.kaos.musa.entities.Reader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends MongoRepository<Reader, String> {
    boolean existsByEmail(String email);
}
