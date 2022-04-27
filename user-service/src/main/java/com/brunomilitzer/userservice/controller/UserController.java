package com.brunomilitzer.userservice.controller;

import com.brunomilitzer.userservice.dto.UserDTO;
import com.brunomilitzer.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    Flux<UserDTO> all() {
        return this.userService.all();
    }

    @GetMapping("{id}")
    Mono<ResponseEntity<UserDTO>> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDTO> createUser(@RequestBody Mono<UserDTO> userDTOMono) {
        return this.userService.createUser(userDTOMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDTO>> updateUser(
            @PathVariable("id") Long id, @RequestBody Mono<UserDTO> userDTOMono) {
        return this.userService.updateUser(id, userDTOMono).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return this.userService.deleteUser(id);
    }
}
