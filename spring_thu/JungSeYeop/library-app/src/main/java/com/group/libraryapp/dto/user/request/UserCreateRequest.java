package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; //null을 표현하기 위해 대문자 Integer이다.

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}