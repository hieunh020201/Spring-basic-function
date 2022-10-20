package com.hieunh.restfulAPI.repository;

import com.hieunh.restfulAPI.entity.Post;
import com.hieunh.restfulAPI.model.dto.AuthorInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(nativeQuery = true, name = "getAuthorInfo")
    AuthorInfoDto getAuthorInfo(int authorId);
}
