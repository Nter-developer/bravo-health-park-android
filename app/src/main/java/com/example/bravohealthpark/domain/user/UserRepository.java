package com.example.bravohealthpark.domain.user;

import java.util.concurrent.CompletableFuture;

public interface UserRepository {
    CompletableFuture<UserEntity> find();
    void save(UserEntity user);
    void delete(String userId);
}
