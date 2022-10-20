package com.hieunh.restfulAPI.service;

import com.hieunh.restfulAPI.entity.Post;
import com.hieunh.restfulAPI.exception.NotFoundException;
import com.hieunh.restfulAPI.model.dto.AuthorInfoDto;
import com.hieunh.restfulAPI.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository postRepository;

    @Override
    public Post getPostById(int id) {

        return null;
    }

    @Override
    public AuthorInfoDto getAuthorById(int id) {
        AuthorInfoDto author = postRepository.getAuthorInfo(id);
        if (author == null) {
            throw new NotFoundException("Không tìm thấy tác giả");
        }


        return author;
    }
}
