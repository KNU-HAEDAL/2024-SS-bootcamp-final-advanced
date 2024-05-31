package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean isUserNotExist(long id) {
        String readSql = "SELECT * FROM user where id=?";
        //값이 존재한다면 0이 나온다 -> 0조차없는 빈 리스트라면 False
        return jdbcTemplate.query(readSql, (rs,rowNum)-> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id) {
        String updatesql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(updatesql, name,id);
    }

    //오버로딩
    public boolean isUserNotExist(String name) {
        String readSql = "SELECT * FROM user where name = ?";
        return jdbcTemplate.query(readSql, (rs,rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM user where name = ?";
        jdbcTemplate.update(sql,name);
    }

    public void saveUser(String name, Integer age) {
        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
        jdbcTemplate.update(sql,name,age);
    }

    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }
}
