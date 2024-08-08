package com.example.controllers;

import com.example.models.Post;
import com.example.models.User;
import com.example.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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


}












//package com.example.controllers;
//
//import com.example.models.Post;
//import com.example.repositories.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.sql.Date;
//
//@Controller
//public class BlogController {
//    @Autowired
//    private PostRepository postRepository;
//
//    @GetMapping("/blog")
//    public String blog(Model model) {
//        return "blog";
//    }
//
//    @GetMapping("/addPost")
//    public String addPostGet(Model model) {
//        return "addPost";
//    }
//
//    @PostMapping("/addPost")
//    public String addPostPost(@RequestParam String categoryStr, @RequestParam String newPostText, Model model) {
//        long category_id = Long.parseLong(categoryStr);
//        long id = 1;
//        LocalDateTime localDateTime = LocalDateTime.now();
//        // Преобразование LocalDateTime в java.util.Date
//        Date utilDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        // Преобразование java.util.Date в java.sql.Date
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//        Post post = new Post(id, category_id, newPostText, postDate);
//        postRepository.save(post);
//        return "redirect:/blog";
//    }
//}
