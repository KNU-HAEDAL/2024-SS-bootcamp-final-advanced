package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.users.User;

public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public  UserResponse(User user){ //25강 jPA 쉽게할려고 만들므
        this.id =user.getID();
        this.name= user.getName();
        this.age = user.getAge();
    }
    //    public UserResponse(long id, String name, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }
    public UserResponse(long id, User user) {
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
