package com.example.trace_spring.repository;

import com.example.trace_spring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember_Email(String email);

//    // 위치 기반 검색을 위한 메소드 (예시)
//    @Query("SELECT p FROM Post p WHERE ...") // 위치 검색을 위한 쿼리 구현
//    List<Post> findNearbyPosts(Double latitude, Double longitude, Double radius);

    // 기타 필요한 메소드 구현
}