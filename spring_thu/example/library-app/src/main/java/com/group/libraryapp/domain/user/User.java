package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id  //primary key로 간주
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동적으로 1씩 증가
    private Long id = null;
    @Column(nullable = false, length = 20, name = "name") //name varchar(20) 객체의 name과 테이블의 name을 매핑함 만약 이름이 같다면 name = "name" 생략가능
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();
    protected User() {}
    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.",name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updataName(String name) {
        this.name = name;
    }

    public void loanBook(String bookname) {
        this.userLoanHistories.add(new UserLoanHistory(this,bookname));
        //add를 해줬는데 save안했는데 디비에 어떻게 저장이 되냐? -> cascade의 역할
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
        //마찬가지로
    }
}
