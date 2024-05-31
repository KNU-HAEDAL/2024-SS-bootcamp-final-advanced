package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JoinColumn(nullable = false)
    @ManyToOne
    //대출기록 : 사용자 = N : 1
    private User user;

    @Column(nullable = false)
    private String bookName;

    @Column()
    private boolean isReturn;

    protected UserLoanHistory(){}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    //반납 설정
    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
