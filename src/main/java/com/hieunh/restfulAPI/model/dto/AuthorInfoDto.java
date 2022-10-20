package com.hieunh.restfulAPI.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class AuthorInfoDto {
    private String full_name;

    private String avatar;

    private List<PostDto> posts;

    public AuthorInfoDto(String full_name, String avatar, Object posts) {
        this.full_name = full_name;
        this.avatar = avatar;
        if (posts != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.posts = mapper.readValue((String) posts, new TypeReference<List<PostDto>>() {
                });
            } catch (IOException e) {
                this.posts = null;
            }
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto{
        private int id;
        private String title;
        private String content;
    }
}
