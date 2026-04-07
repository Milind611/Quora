package com.example.QuoraClone.service;

import com.example.QuoraClone.dtos.UserDTO;
import com.example.QuoraClone.models.Tag;
import com.example.QuoraClone.models.User;
import com.example.QuoraClone.repositories.TagRepository;
import com.example.QuoraClone.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public UserService(UserRepository userRepository,TagRepository tagRepository)
    {
        this.userRepository=userRepository;
        this.tagRepository=tagRepository;
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id)
    {
        return userRepository.findById(id);
    }

    public User createUser(UserDTO userDTO)
    {
        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }
    public void deleteByUserID(Long id)
    {
        userRepository.deleteById(id);
    }

    public void followTag(Long userId, Long tagId)
    {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Tag tag=tagRepository.findById(tagId).orElseThrow(()->new RuntimeException("Tag not found"));
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }
}
