package com.example.trace_spring.controller;

import com.example.trace_spring.entity.Post;
import com.example.trace_spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public String createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return "Created post with ID: " + newPost.getId();
    }

    // 추가적인 컨트롤러 메소드 구현
}
