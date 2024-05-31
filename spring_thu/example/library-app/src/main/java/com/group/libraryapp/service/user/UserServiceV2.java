package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //아래 있는 함수가 시작될 때 start transaction을 해준다
    //함수가 예외 없이 잘 끝났다면 commit
    //혹시라도 문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(),request.getAge()));
        //u.getId();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new).collect(Collectors.toList());
    }

    @Transactional()
    public void updateUser(UserUpdateRequest request) {
        //select * from user where id = ?;
        //Optional<User> -> 유저를 찾고 있는지 없는지 확인

        //유저를 찾고, 없다면 예외 있으면 결과 반환
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalAccessError::new);
        //객체를 가져오고 그 객체를 update한다. (함수 호출)
        user.updataName(request.getName());

        //업데이트한 유저 저장
        //userRepository.save(user);  -> 영속성 컨텍스트 안에서 시행되기에 함수가 종료될 때 자동으로 변경사항을 감지하고 자동으로 저장된다

        //자동으로 UPDATE SQL이 날아가게 된다.
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
