package com.example.QuoraClone.repositories;

import com.example.QuoraClone.models.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>
{
    Page<Comment> findByAnswerId(Long id, Pageable pageable);

    Page<Comment> findByParentCommentId(Long id,Pageable pageable);
}
