package com.hieunh.restfulAPI.service;

import com.hieunh.restfulAPI.entity.User;
import com.hieunh.restfulAPI.exception.NotFoundException;
import com.hieunh.restfulAPI.model.dto.UserDto;
import com.hieunh.restfulAPI.model.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService{
    private static ArrayList<User> users = new ArrayList<User>();

    static {
        users.add(new User(1, "Nguyễn Hữu Hiếu 1", "huuhieu1@gmail.com", "0123456798", "avatar.png","123"));
        users.add(new User(2, "Nguyễn Hữu Hiếu 2", "huuhieu2@gmail.com", "0123456798", "avatar.png","123"));
        users.add(new User(3, "Nguyễn Hữu Hiếu 3", "huuhieu3@gmail.com", "0123456798", "avatar.png","123"));
        users.add(new User(4, "Nguyễn Hữu Hiếu 4", "huuhieu4@gmail.com", "0123456798", "avatar.png","123"));
    }

    @Override
    public List<UserDto> getListUser() {
        List<UserDto> result = new ArrayList<UserDto>();
        for(User user: users) {
            result.add(UserMapper.toUserDto(user));
        }
        return result;
    }

    @Override
    public UserDto getUserById(int id) {
        for (User user: users) {
            if (user.getId() == id) {
                return UserMapper.toUserDto(user);
            }
        }
        throw new NotFoundException("User không tồn tại trong hệ thống");
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<UserDto> result = new ArrayList<>();
        for (User user: users) {
            if (user.getName().contains(keyword)) {
                result.add(UserMapper.toUserDto(user));
            }
        }
        return result;
    }
}
