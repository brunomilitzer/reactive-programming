package com.brunomilitzer.userservice.service;

import com.brunomilitzer.userservice.dto.UserDTO;
import com.brunomilitzer.userservice.repository.UserRepository;
import com.brunomilitzer.userservice.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<UserDTO> all() {
        return this.userRepository.findAll().map(EntityDTOUtil::toDTO);
    }

    public Mono<UserDTO> getUserById(final Long id) {
        return this.userRepository.findById(id).map(EntityDTOUtil::toDTO);
    }

    public Mono<UserDTO> createUser(Mono<UserDTO> userDTOMono) {
        return userDTOMono.map(EntityDTOUtil::toEntity).flatMap(this.userRepository::save).map(EntityDTOUtil::toDTO);
    }

    public Mono<UserDTO> updateUser(Long id, Mono<UserDTO> userDTOMono) {
        return this.userRepository.findById(id).flatMap(u -> userDTOMono.map(EntityDTOUtil::toEntity)
                .doOnNext(e -> e.setId(id))).flatMap(this.userRepository::save).map(EntityDTOUtil::toDTO);
    }

    public Mono<Void> deleteUser(Long id) {
        return this.userRepository.deleteById(id);
    }
}
