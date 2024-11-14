package com.kaos.musa.controllers;

import com.kaos.musa.entities.Post;
import com.kaos.musa.entities.User;
import com.kaos.musa.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private BlogService service;

    @GetMapping("/blog")
    public String blog(Model model, @RequestParam(defaultValue = "0") int page){
        Page<Post> postPage = service.findAll(page);

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("recentPosts", service.recentPosts());
        return "pages/blog";
    }

    @GetMapping("/blog/newpost")
    public String newPost(Model model){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            var userDetails = (User) authentication.getPrincipal();
            model.addAttribute("authorName", userDetails.getName());
        }
        return "pages/newpost";
    }

    @PostMapping("/blog/newpost")
    public String createPost(Post post){
        service.createPost(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/post/{id}")
    public String post(@PathVariable String id, Model model){
        model.addAttribute("post", service.findPost(id));
        model.addAttribute("recentPosts", service.recentPosts());
        return "pages/post";
    }




}
