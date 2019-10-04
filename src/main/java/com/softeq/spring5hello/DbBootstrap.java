package com.softeq.spring5hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Component
public class DbBootstrap {

    @Autowired
    DatabaseClient databaseClient;

    @PostConstruct
    void init() {
        databaseClient
            .select()
            .from("users")
            .as(User.class)
            .fetch().first()
            .toFuture()
            .exceptionally((ex) -> {
                this
                    .createDatabase()
                    .toFuture()
                    .thenRun(this::insertFirstUser);

                return null;
            });
    }

    private Mono<Void> createDatabase() {
        return databaseClient
            .execute("create table users(" +
                "id int not null primary key, " +
                "first_name varchar(255) not null, " +
                "last_name varchar(255) not null)"
            )
            .fetch()
            .rowsUpdated()
            .then();
    }

    private void insertFirstUser() {
        databaseClient
            .insert()
            .into("users")
            .value("id", 1)
            .value("first_name", "Joe")
            .value("last_name", "Doe")
            .fetch()
            .rowsUpdated()
            .toFuture();
    }

}
