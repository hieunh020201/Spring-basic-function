package com.hieunh.restfulAPI.service;

import com.hieunh.restfulAPI.entity.Post;
import com.hieunh.restfulAPI.model.dto.AuthorInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    public Post getPostById(int id);

    public AuthorInfoDto getAuthorById(int id);
}
