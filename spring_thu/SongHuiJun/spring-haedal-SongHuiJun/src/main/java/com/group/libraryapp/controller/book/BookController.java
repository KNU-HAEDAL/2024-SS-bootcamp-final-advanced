package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.request.BookCreatRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

//    private BookService bookService =new BookService(); 원래는 이렇게 함
    private final BookService bookService;
// 10번째 줄 주석 처럼 안하고 빈 을 사용하니 아래 처럼 생성자 만듦

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody BookCreatRequest request){
        bookService.saveBook(request);
    }


    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest request){
        bookService.loanBook(request);
    }


    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request){
        bookService.returnBook(request);
    }
}
