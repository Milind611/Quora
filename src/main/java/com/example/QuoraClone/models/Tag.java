package com.example.QuoraClone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Tag extends Basemodel
{
    private String name;

    @ManyToMany(mappedBy = "followedTags")
    private Set<User> followers;
}
