package com.hieunh.restfulAPI.controller;

import com.hieunh.restfulAPI.model.dto.AuthorInfoDto;
import com.hieunh.restfulAPI.model.dto.UserDto;
import com.hieunh.restfulAPI.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("author/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id) {
        AuthorInfoDto result = postService.getAuthorById(id);
        return ResponseEntity.ok(result);
    }
}
