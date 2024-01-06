package com.example.trace_spring;

import lombok.Getter;

@Getter
public class MemberDTO {
    private String name;
    private String email;
    private String password;

    // 생성자, 게터, 세터 등 필요한 메서드 추가 가능

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
