package com.example.trace_spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content")
    private String content;

    @Column(name="latitude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;

    @Column(name="email")
    private String email;

    @Lob
    @Column(name="image", columnDefinition="TEXT")
    private String image;  // Base64 인코딩된 이미지 데이터를 저장할 필드

    @Lob
    @Column(name="imageExtra", columnDefinition="TEXT")
    private String imageExtra;  // Base64 인코딩된 이미지 데이터를 저장할 필드

    @Column(name="address")
    private String address;  // 추가된 주소 필드

}