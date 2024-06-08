package com.group.libraryapp.domain.users;

import com.group.libraryapp.domain.users.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
    @Column(nullable = false,length = 20,name="name")//name varchar(20)
    private String name;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories =new ArrayList<>();
    private Integer age;//column 어노테이션 생략가능 테이블에 있는거랑 완벽히 똑같아서


    protected User(){}

    public User(String name, Integer age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getID(){return id;}


    public void updateName(String name){
        this.name =name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this,bookName));
    }


    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history ->history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }

}
