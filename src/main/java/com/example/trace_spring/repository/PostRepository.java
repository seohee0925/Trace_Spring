package com.example.trace_spring.repository;

import com.example.trace_spring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByEmail(String email);

    @Query("SELECT p FROM Post p WHERE p.latitude >= :southWestLat AND p.latitude <= :northEastLat AND p.longitude >= :southWestLng AND p.longitude <= :northEastLng")
    List<Post> findPostsInArea(@Param("southWestLat") double southWestLat,
                               @Param("southWestLng") double southWestLng,
                               @Param("northEastLat") double northEastLat,
                               @Param("northEastLng") double northEastLng);
    Optional<Post> findById(Long id);
}