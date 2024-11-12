package com.kaos.musa.services;

import com.kaos.musa.entities.Post;
import com.kaos.musa.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Post> findAll(int page){
       return repository.findAll(PageRequest.of(page, 4));
    }

    public Post findPost(String id){
        if(repository.findById(id).isEmpty()){
            throw new RuntimeException("Não encontrado");
        }
        return repository.findById(id).get();
    }

    public List<Post> recentPosts(){
        return repository.findTop3ByOrderByDateDesc();
    }
}
