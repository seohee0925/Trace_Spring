package com.example.trace_spring.controller;

import com.example.trace_spring.entity.Member;
import org.springframework.http.ResponseEntity;
import com.example.trace_spring.entity.Post;
import com.example.trace_spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

        List<Map<String, Object>> postsResponse = new ArrayList<>();
        ZoneId serverZone = TimeZone.getDefault().toZoneId(); // 서버의 기본 타임존 가져오기
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME; // ISO 8601 형식 포맷터

        for (Post post : posts) {
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("id", post.getId());
            postMap.put("content", post.getContent());
            postMap.put("latitude", post.getLatitude());
            postMap.put("longitude", post.getLongitude());
            postMap.put("email", post.getEmail());
            postMap.put("image", post.getImage());
            postMap.put("imageExtra", post.getImageExtra());
            postMap.put("address", post.getAddress());

            // 시간대 변환
            ZonedDateTime zonedDateTime = post.getCreatedDate().toInstant().atZone(serverZone);
            ZonedDateTime userZoneTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul")); // 사용자의 타임존 설정

            // ISO 8601 형식의 문자열로 변환하여 넣기
            String formattedDateTime = userZoneTime.format(formatter);
            postMap.put("createdDate", formattedDateTime);

            postsResponse.add(postMap);
        }

        return ResponseEntity.ok(postsResponse);
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

    @GetMapping("/byId/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if(post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
