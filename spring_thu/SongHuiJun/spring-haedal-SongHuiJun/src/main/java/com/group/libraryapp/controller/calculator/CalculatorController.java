package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.domain.users.Fruit;
import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CalculatorController {
    @GetMapping("/add") // Get/add
    public  int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1()+request.getNumber2();
    }

    @PostMapping("/multiply")// Post /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNumber1()* request.getNumber2();
    }
    @GetMapping("/fruit")
    public Fruit fruit(){
        return new Fruit("바나나",2000);
    }






}
