package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserupdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceV1 {

    private  final UserJdbcRepository userJdbcRepository;
    private final FruitService fruitService;
    public UserServiceV1(UserJdbcRepository userJdbcRepository, @Qualifier("main") FruitService fruitService){
        this.userJdbcRepository = userJdbcRepository;
        this.fruitService=fruitService;
    }

    public void updateUser( UserupdateRequest request){

//        boolean isUserNotExist =userRepository.isUserNotExist(jdbcTemplate,request.getId());
       if(userJdbcRepository.isUserNotExist(request.getId())){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUserName(request.getName(),request.getId());
    }


    public void deleteUser(String name){

        if(userJdbcRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUser(name);

    }

    public void saveUser(UserCreateRequest request){
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }


    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();
    }


}
