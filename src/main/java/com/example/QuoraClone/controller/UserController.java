package com.example.QuoraClone.controller;

import com.example.QuoraClone.dtos.UserDTO;
import com.example.QuoraClone.models.User;
import com.example.QuoraClone.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        Optional<User> user=userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO)
    {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteByUserID(id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/followTag/{tagId}")
    public ResponseEntity<Void> followTag(@PathVariable Long userId,@PathVariable Long tagId)
    {
        userService.followTag(userId,tagId);
        return ResponseEntity.noContent().build();
    }
}
