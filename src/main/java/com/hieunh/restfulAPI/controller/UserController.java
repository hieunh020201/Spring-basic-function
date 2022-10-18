package com.hieunh.restfulAPI.controller;

import com.hieunh.restfulAPI.entity.User;
import com.hieunh.restfulAPI.model.dto.UserDto;
import com.hieunh.restfulAPI.request.CreateUserReq;
import com.hieunh.restfulAPI.request.UpdateUserReq;
import com.hieunh.restfulAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<UserDto> users = userService.getListUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        List<UserDto> users = userService.searchUser(name);
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserReq req,@PathVariable int id) {
        UserDto result = userService.updateUser(req, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete success");
    }
}
