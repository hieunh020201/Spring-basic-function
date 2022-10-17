package com.hieunh.restfulAPI.controller;

import com.hieunh.restfulAPI.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public ResponseEntity<?> helloWorld() {
        User user = new User();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
    }
}
