package com.example.trace_spring.controller;

import com.example.trace_spring.data.MemberDTO;
import com.example.trace_spring.entity.Member;
import com.example.trace_spring.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody MemberDTO memberDTO) {
        Member newMember = memberService.register(memberDTO);
        if (newMember != null) {
            return new ResponseEntity<>(newMember, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Member> loginMember(@RequestParam String email, @RequestParam String password) {
        Member loggedInMember = memberService.login(email, password);
        if (loggedInMember != null) {
            return ResponseEntity.ok(loggedInMember);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
