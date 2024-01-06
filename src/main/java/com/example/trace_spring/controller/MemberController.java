package com.example.trace_spring.controller;

import com.example.trace_spring.entity.Member;
import com.example.trace_spring.MemberDTO;
import com.example.trace_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public String registerMember(@RequestBody MemberDTO memberDTO) {
        Member newMember = memberService.register(memberDTO);
        return "Registered member with ID: " + newMember.getId();
    }
}
