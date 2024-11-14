package com.kaos.musa.repositories;

import com.kaos.musa.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Post, String> {

    List<Post> findTop3ByOrderByDateDesc();

    Page<Post> findAllByOrderByDateDesc(Pageable pageable);

}
