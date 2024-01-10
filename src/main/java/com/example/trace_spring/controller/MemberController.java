package com.example.trace_spring.controller;

import com.example.trace_spring.data.MemberDTO;
import com.example.trace_spring.entity.Member;
import com.example.trace_spring.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Member> loginMember(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Member loggedInMember = memberService.login(email, password);
        if (loggedInMember != null) {
            session.setAttribute("userEmail", email);
            return ResponseEntity.ok(loggedInMember);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("userEmail");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberEmail}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable String memberEmail) {
        Member member = memberService.getMemberByEmail(memberEmail);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}