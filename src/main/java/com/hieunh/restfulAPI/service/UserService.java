package com.hieunh.restfulAPI.service;

import com.hieunh.restfulAPI.model.dto.UserDto;
import com.hieunh.restfulAPI.request.CreateUserReq;
import com.hieunh.restfulAPI.request.UpdateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getListUser();

    public UserDto getUserById(int id);

    public List<UserDto> searchUser(String keyword);

    public UserDto createUser(CreateUserReq req);

    public UserDto updateUser(UpdateUserReq req, int id);

    public boolean deleteUser(int id);
}
