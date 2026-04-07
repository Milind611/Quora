package com.example.QuoraClone.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class CommentDTO
{
    private Long id;
    private String content;
    private Long answerId;
    private Long parentCommentId;
}
