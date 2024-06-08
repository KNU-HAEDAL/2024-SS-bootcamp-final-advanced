package com.group.libraryapp.service.book;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.users.User;
import com.group.libraryapp.domain.users.UserRepository;
import com.group.libraryapp.domain.users.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.users.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreatRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
//    private final BookRepository bookRepository
//            =new BookMySqlRepository(); 이런 식으로 하면 메모리 레포지토리를 쓸려면 다 바꿔야함
    //그래서 아래 처럼 컨테이너가 할 수 있게한다.
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository
    ,UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository= userLoanHistoryRepository;
        this.userRepository = userRepository;


    }
    @Transactional
    public void saveBook(BookCreatRequest request){
        bookRepository.save(new Book(request.getName()));
    }


    @Transactional
    public void loanBook(BookLoanRequest request){
        //1.책정보를 가져온다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false))
        {
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다");
        }

        //4.유저 정보를 가져온다.
        User user=userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        //5.유저 정보와 책 정보를 기반으로 UserLoanHistory 저장
        user.loanBook(book.getName());

    }


    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);


        user.returnBook(request.getUserName());
    }


}




