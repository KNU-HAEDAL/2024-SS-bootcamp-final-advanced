package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
    @Column(nullable = false,length = 255,name="name")//e뒤에 ""안에있는게 테이블의 네임임 근데 필드랑 똑같아서 생략가능
    private String name;

    protected Book(){

    }

    public Book(String name) {
        if(name ==null || name.isBlank()){
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
