package com.example.trace_spring.repository;

import com.example.trace_spring.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmailAndPassword(String email, String password);
    Member findByEmail(String email);
    List<Member> findAll();
}