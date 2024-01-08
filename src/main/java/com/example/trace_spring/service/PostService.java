package com.example.trace_spring.service;

import com.example.trace_spring.entity.Member;
import com.example.trace_spring.entity.Post;
import com.example.trace_spring.repository.MemberRepository;
import com.example.trace_spring.repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;

@Slf4j
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HttpSession httpSession;

    public Post createPost(Post post) {
        String userEmail = (String) httpSession.getAttribute("userEmail");

        if (userEmail != null) {
            Member member = memberRepository.findByEmail(userEmail);
            if (member != null) {
                post.setMember(member);
                return postRepository.save(post);
            } else {
                log.error("Could not find member with email: {}", userEmail);
            }
        } else {
            log.error("User email in session is null");
        }
        return null;
    }




    public List<Post> findByEmail(String email) {
        return postRepository.findByMember_Email(email);
    }

    // 위치 기반 검색 기능을 위한 메소드
    public List<Post> findNearbyPosts(Double latitude, Double longitude, Double radius) {
        // 위치 기반 검색 로직 구현
        return null;
    }

    // 게시물 삭제 기능
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 기타 필요한 메소드 구현
}
