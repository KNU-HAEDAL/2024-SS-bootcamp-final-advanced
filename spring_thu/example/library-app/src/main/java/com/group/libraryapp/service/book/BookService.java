package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 책 정보 가져오기 (이름으로)
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출 기록 정보 확인해서 대출중인지 확인
        // existsByBookNameAndIsReturn 얘를 호출했을 때 name과 false를 준다면 -> 대출중인상태
        // 3. 대출 중이라면 예외 발생
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)) {
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다.");
        }

        //4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        //유저에서 책을 userloanhitories 추가
        user.loanBook(book.getName());
//        //5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
//        userLoanHistoryRepository.save(new UserLoanHistory(user,book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        //1. 유저정보 가져오기
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
//        // 2. 대출기록 찾기
//        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
//                .orElseThrow(IllegalArgumentException::new);
//        // 3. 대출이력 수정
//        history.doReturn();

        //user 도메인의 함수 호출
        user.returnBook(request.getBookName());

    }
}
