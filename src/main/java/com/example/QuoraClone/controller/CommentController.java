package com.example.QuoraClone.controller;

import com.example.QuoraClone.dtos.CommentDTO;
import com.example.QuoraClone.models.Comment;
import com.example.QuoraClone.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController
{
    private CommentService commentService;

    public CommentController(CommentService commentService)
    {
        this.commentService=commentService;
    }

    @GetMapping("/answer/{answerId}")
    public List<Comment> getCommentByAnswerId(@PathVariable Long answerId, @RequestParam int page,@RequestParam int size)
    {
        return commentService.getCommentsByAnswerId(answerId,page,size);
    }

    @GetMapping("/comment/{commentId}")
    public List<Comment> getCommentByCommentId(@PathVariable Long commentId, @RequestParam int page,@RequestParam int size)
    {
        return commentService.getRepliesByCommentId(commentId,page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id)
    {
        Optional<Comment> comment=commentService.getByCommentId(id);
        return comment.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDTO commentDTO)
    {
        return commentService.createComment(commentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id)
    {
        commentService.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
