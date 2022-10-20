package com.hieunh.restfulAPI.service;

import com.hieunh.restfulAPI.entity.User;
import com.hieunh.restfulAPI.exception.DuplicateRecordException;
import com.hieunh.restfulAPI.exception.InternalServerException;
import com.hieunh.restfulAPI.exception.NotFoundException;
import com.hieunh.restfulAPI.model.dto.UserDto;
import com.hieunh.restfulAPI.model.mapper.UserMapper;
import com.hieunh.restfulAPI.repository.UserRepository;
import com.hieunh.restfulAPI.request.CreateUserReq;
import com.hieunh.restfulAPI.request.UpdateUserReq;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
//    private static ArrayList<User> users = new ArrayList<User>();

//    static {
//        users.add(new User(1, "Nguyễn Hữu Hiếu 1", "huuhieu1@gmail.com", "0123456798", "avatar.png","123"));
//        users.add(new User(2, "Nguyễn Hữu Hiếu 2", "huuhieu2@gmail.com", "0123456798", "avatar.png","123"));
//        users.add(new User(3, "Nguyễn Hữu Hiếu 3", "huuhieu3@gmail.com", "0123456798", "avatar.png","123"));
//        users.add(new User(4, "Nguyễn Hữu Hiếu 4", "huuhieu4@gmail.com", "0123456798", "avatar.png","123"));
//    }

    @Override
    public List<UserDto> getListUser() {
        List<User> users = userRepository.findAll();

//        Convert to UserDto
        List<UserDto> result = new ArrayList<UserDto>();
        for(User user: users) {
            result.add(UserMapper.toUserDto(user));
        }
        return result;
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);

//        User user = userRepository.getUserInfo(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User không tồn tại trong hệ thống");
        }
        return UserMapper.toUserDto(user.get());
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
//        List<UserDto> result = new ArrayList<>();
//        for (User user: users) {
//            if (user.getName().contains(keyword)) {
//                result.add(UserMapper.toUserDto(user));
//            }
//        }
//        return result;
        return null;
    }

    @Override
    public UserDto createUser(CreateUserReq req) {
//        Kiểm tra email tồn tại
        User rs = userRepository.getUserInfoByEmail(req.getEmail());
        if (rs != null) {
            throw new InternalServerException("Email is already in system");
        }

        //        Convert CreateUserReq -> User
        User user = new User();
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setPhone(req.getPhone());
        //        Mã hóa mật khẩu
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));

        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't save user");
        }
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(UpdateUserReq req, int id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()){
            throw new NotFoundException("No user found");
        }
        User user = result.get();

//        Update info
        user.setName(req.getName());
        user.setPhone(req.getPhone());
        user.setAvatar(req.getAvatar());
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));

        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't update user");
        }

        return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't update user");
        }
    }
}
