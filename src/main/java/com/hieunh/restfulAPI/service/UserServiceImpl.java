package com.hieunh.restfulAPI.service;

import com.hieunh.restfulAPI.entity.User;
import com.hieunh.restfulAPI.exception.DuplicateRecordException;
import com.hieunh.restfulAPI.exception.NotFoundException;
import com.hieunh.restfulAPI.model.dto.UserDto;
import com.hieunh.restfulAPI.model.mapper.UserMapper;
import com.hieunh.restfulAPI.request.CreateUserReq;
import com.hieunh.restfulAPI.request.UpdateUserReq;
import org.mindrot.jbcrypt.BCrypt;
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

    @Override
    public UserDto createUser(CreateUserReq req) {

        for (User user: users) {
            if (user.getEmail().equals(req.getEmail())) {
                throw new DuplicateRecordException("Email đã tồn tại");
            }
        }
//        Convert CreateUserReq -> User
        User user = new User();
        user.setId(users.size() + 1);
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setPhone(req.getPhone());

//        Mã hóa mật khẩu
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));

        users.add(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(UpdateUserReq req, int id) {
        for (User user: users) {
            if (user.getId() == id) {
                if (!user.getEmail().equals(req.getEmail())) {
                    for (User tmp: users) {
                        if (tmp.getEmail().equals(req.getEmail())) {
                            throw new DuplicateRecordException("New email already exists in the system");
                        }
                    }
                    user.setEmail(req.getEmail());
                }
                user.setName(req.getName());
                user.setPhone(req.getPhone());
                user.setAvatar(req.getAvatar());
                user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
                return UserMapper.toUserDto(user);
            }
        }
        throw new NotFoundException("No user found");
    }

    @Override
    public boolean deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return true;
            }
        }

        throw new NotFoundException("No user found");
    }
}
