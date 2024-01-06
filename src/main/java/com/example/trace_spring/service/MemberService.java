package com.example.trace_spring.service;

import com.example.trace_spring.MemberDTO;
import com.example.trace_spring.entity.Member;
import com.example.trace_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member register(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());
        // 기타 필요한 로직 수행 가능

        return memberRepository.save(member);
    }
}
