package com.example.trace_spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String name;

    @Column(name="user_email")
    private String email;

    @Column(name="user_pw")
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> postList;
}
