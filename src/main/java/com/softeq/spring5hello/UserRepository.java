package com.softeq.spring5hello;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    @Query("SELECT * FROM users")
    Flux<User> findAll();

}
