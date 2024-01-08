package com.example.trace_spring.controller;

import com.example.trace_spring.entity.Member;
import com.example.trace_spring.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import com.example.trace_spring.entity.Post;
import com.example.trace_spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        Post post = new Post();
        post.setContent((String) requestBody.get("content"));
        post.setLatitude((Double) requestBody.get("latitude"));
        post.setLongitude((Double) requestBody.get("longitude"));
        String userEmail = (String) session.getAttribute("user_email");

        Member member = memberService.getMemberByEmail(userEmail);

        post.setMember(member);
        Post newPost = postService.createPost(post);
//        if (member != null) {
//            post.setMember(member); // Post 엔티티에 사용자 연결
//            Post newPost = postService.createPost(post);

            if (newPost != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", newPost.getId());
                response.put("name", newPost.getMember().getName());
                response.put("email", newPost.getMember().getEmail());
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Failed to create post");
                return ResponseEntity.badRequest().body(errorResponse);
            }
//        } else {
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("error", "User not found");
//            return ResponseEntity.badRequest().body(errorResponse);
//        }
    }

}
