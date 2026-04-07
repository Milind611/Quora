package com.example.QuoraClone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Question extends Basemodel
{
    private String title;
    private String content;

    @ManyToMany
    @JoinTable(
            name = "question-tags",
            joinColumns = @JoinColumn(name = "qustion_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
