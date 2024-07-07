package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.entity.UserEntity;
import com.example.IfGoiano.IfCoders.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAllUsers() {
        List<UserEntity> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        UserEntity createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}/update-visto-por-ultimo")
    public ResponseEntity<Void> updateVistoPorUltimoById(@PathVariable Long id){
        userService.updateVistoPorUltimoById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@Valid @RequestBody UserEntity user) {
        UserEntity updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/contatos/{id}")
    public ResponseEntity<List<UserEntity>> findUsersWithMessagesExchange(@PathVariable Long id) {
        List<UserEntity> users = userService.findContactsByUserId(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}