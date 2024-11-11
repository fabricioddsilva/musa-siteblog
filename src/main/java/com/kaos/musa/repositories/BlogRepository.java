package com.kaos.musa.repositories;

import com.kaos.musa.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Post, String> {

}
