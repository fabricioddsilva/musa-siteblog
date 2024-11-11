package com.kaos.musa.services;

import com.kaos.musa.entities.Post;
import com.kaos.musa.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;

    public void createPost(Post post){
        post.setDate(LocalDate.now());
        repository.save(post);
    }

    public List<Post> findAllPosts(){
       return repository.findAll();
    }

    public Post findPost(String id){
        if(repository.findById(id).isEmpty()){
            throw new RuntimeException("Não encontrado");
        }
        return repository.findById(id).get();
    }
}
