package com.aiwa.us.service;

import com.aiwa.us.dto.UserDto;
import com.aiwa.us.repository.UserRepository;
import com.aiwa.us.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository mUserRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    public Flux<UserDto> fetchAllUsers() {
        return this.mUserRepository
                .findAll()
                .map(EntityToDto::toUserDto);
    }

    public Mono<UserDto> fetchUserById(final Long userId) {
        return this.mUserRepository
                .findById(userId)
                .map(EntityToDto::toUserDto);
    }

    public Mono<UserDto> saveUser(final Mono<UserDto> userDtoMono) {
        return userDtoMono
                .map(EntityToDto::toUserEntity) // first convert our dto to an entity
                .flatMap(this.mUserRepository::save) // save that entity
                .map(EntityToDto::toUserDto); // convert back that entity ot dto
    }

    public Mono<UserDto> updateUser(final Long userId, final Mono<UserDto> userDtoMono) {
        return this.mUserRepository
                .findById(userId) // get entity (Next lines will be executed only if there is that entity)
                .flatMap(user -> userDtoMono // so, entity is presents
                        .map(EntityToDto::toUserEntity) // convert our dto to entity
                        .doOnNext(u -> u.setId(userId))) // once converted to entity set its ID with one provided
                .flatMap(this.mUserRepository::save) // save it (will update as entity with that ID already exist)
                .map(EntityToDto::toUserDto); // convert back entity to dto
    }

    public Mono<Void> removeUser(final Long userId) {
        return this.mUserRepository.deleteById(userId);
    }
}
