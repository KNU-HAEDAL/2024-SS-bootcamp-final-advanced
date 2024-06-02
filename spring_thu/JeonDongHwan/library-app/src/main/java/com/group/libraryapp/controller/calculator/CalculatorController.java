package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//API가 현재 메소드를 사용하도록 만든다
public class CalculatorController {

    @GetMapping("/add") // GET /add
    //HTTP Method 설정
    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply") // Post /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNumber1() * request.getNumber2();
    }

}
