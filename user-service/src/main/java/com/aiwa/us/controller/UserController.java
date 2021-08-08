package com.aiwa.us.controller;

import com.aiwa.us.dto.UserDto;
import com.aiwa.us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/user"})
public class UserController {

    @Autowired
    private final UserService mUserService;

    public UserController(UserService userService) {
        mUserService = userService;
    }

    @GetMapping(path = {"/all"})
    public Flux<UserDto> allUsers() {
        return this.mUserService.fetchAllUsers();
    }

    @GetMapping(path = {"/{userId}"})
    public Mono<ResponseEntity<UserDto>> userById(final @PathVariable("userId") Long userId) {
        return this.mUserService
                .fetchUserById(userId) // if user is present
                .map(ResponseEntity::ok) // then, return it with status OK 200
                .defaultIfEmpty(ResponseEntity.notFound().build()); // otherwise, 404 NOT FOUND
    }

    @PostMapping
    public Mono<ResponseEntity<UserDto>> addUser(final @RequestBody Mono<UserDto> userDtoMono) {
        return this.mUserService
                .saveUser(userDtoMono)
                .map(ResponseEntity::ok);
    }

    @PutMapping(path = {"/{userId}"})
    public Mono<ResponseEntity<UserDto>> updateUser(
            final @PathVariable("userId") Long userId,
            final @RequestBody Mono<UserDto> userDtoMono) {
        return this.mUserService
                .updateUser(userId, userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(path = {"/{userId}"})
    public Mono<ResponseEntity<Void>> deleteUser(final @PathVariable("userId") Long userId) {
        return this.mUserService
                .removeUser(userId)
                .map(ResponseEntity::ok);
    }
}
