package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;

    @ManyToOne
    private User user;

    private String bookName;

    //boolean으로 사용시 tinyInt와 알아서 잘 매핑이 된다.
    private boolean isReturn;

    protected UserLoanHistory(){

    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName(){
        return this.bookName;
    }
}
