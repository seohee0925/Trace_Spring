package com.example.trace_spring.controller;

import com.example.trace_spring.entity.Member;
import org.springframework.http.ResponseEntity;
import com.example.trace_spring.entity.Post;
import com.example.trace_spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Created post with ID: " + newPost.getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberEmail}")
    public ResponseEntity<Object> getPostsByMemberEmail(@PathVariable String memberEmail) {
        List<Post> posts = postService.findByEmail(memberEmail);

        if(posts.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "No posts found for the member with email: " + memberEmail);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/inArea")
    public ResponseEntity<Object> getPostsInArea(
            @RequestParam double southWestLat,
            @RequestParam double southWestLng,
            @RequestParam double northEastLat,
            @RequestParam double northEastLng) {

        List<Post> posts = postService.findPostsInArea(southWestLat, southWestLng, northEastLat, northEastLng);

        return ResponseEntity.ok(posts);
    }

}
