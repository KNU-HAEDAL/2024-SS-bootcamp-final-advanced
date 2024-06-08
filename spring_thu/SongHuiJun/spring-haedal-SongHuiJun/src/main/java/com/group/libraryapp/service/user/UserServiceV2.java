package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.users.User;
import com.group.libraryapp.domain.users.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserupdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository){
        this.userRepository =userRepository;

    }
    //아래 있는 함수가 시작될때 start transaction;을 해준다
    //함수가 예외 없이 잘끝났다면 commit
    //혹시라도 문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request){
        User u=userRepository.save(new User(request.getName(),request.getAge()));
//        throw new IllegalArgumentException(); 트랜섹션 확인용
    }




    @Transactional
    public List<UserResponse> getUsers(){
        List<User> users =userRepository.findAll();
        return users.stream().map(UserResponse::new)//stream 이용한것
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserupdateRequest request){
        User user =userRepository.findById(request.getId())
                .orElseThrow(IllegalAccessError::new);

        user.updateName(request.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name){
//        //SELECT * FROM user WHRER name =?
        User user =userRepository.findByName(name).orElseThrow();

        userRepository.delete(user);
    }
}
