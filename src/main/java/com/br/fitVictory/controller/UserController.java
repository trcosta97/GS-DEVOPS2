package com.br.fitVictory.controller;

import com.br.fitVictory.domain.user.User;
import com.br.fitVictory.domain.user.UserCadastroDTO;
import com.br.fitVictory.domain.user.UserUpdateDTO;
import com.br.fitVictory.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid UserCadastroDTO data, UriComponentsBuilder uriBuilder){
        var newUser = new User(data);
        User savedUser = service.save(newUser);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        var user = service.get(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO data){
        var updatedData = new User(data);
        var updatedUser = service.update(id, updatedData);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
