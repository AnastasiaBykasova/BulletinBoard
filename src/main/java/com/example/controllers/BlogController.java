package com.example.controllers;

import com.example.models.Post;
import com.example.models.User;
import com.example.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("/addPost")
    public String addPostGet(Model model) {
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPostPost(@RequestParam String categoryStr, @RequestParam String newPostTheme, @RequestParam String newPostText, Model model) {
        long category_id = Long.parseLong(categoryStr);
        long user_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        Post post = new Post(user_id, category_id, newPostTheme, newPostText, timestamp);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String viewPost(@PathVariable(value = "id") Long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("viewPost", res);
        return "viewPost";
    }


}
