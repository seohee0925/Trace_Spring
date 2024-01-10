package com.example.trace_spring.service;

import com.example.trace_spring.entity.Post;
import com.example.trace_spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findByEmail(String email) {
        return postRepository.findPostByEmail(email);
    }

    // 위치 기반 검색 기능을 위한 메소드
    public List<Post> findNearbyPosts(Double latitude, Double longitude, Double radius) {
        // 위치 기반 검색 로직 구현
        return null;
    }

    public boolean deletePostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Post> findPostsInArea(double southWestLat, double southWestLng, double northEastLat, double northEastLng) {
        return postRepository.findPostsInArea(southWestLat, southWestLng, northEastLat, northEastLng);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
