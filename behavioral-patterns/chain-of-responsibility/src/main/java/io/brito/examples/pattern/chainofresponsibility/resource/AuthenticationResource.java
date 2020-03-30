package io.brito.examples.pattern.chainofresponsibility.resource;

import io.brito.examples.pattern.chainofresponsibility.handler.AuthenticationHandler;
import io.brito.examples.pattern.chainofresponsibility.handler.validation.ValidateNullObject;
import io.brito.examples.pattern.chainofresponsibility.handler.validation.ValidatePassword;
import io.brito.examples.pattern.chainofresponsibility.handler.validation.ValidateRequestLimit;
import io.brito.examples.pattern.chainofresponsibility.handler.validation.ValidateUser;
import io.brito.examples.pattern.chainofresponsibility.resource.model.AuthenticationRequest;
import io.brito.examples.pattern.chainofresponsibility.util.Database;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationResource {

    private AuthenticationHandler authenticationHandler;
    private Database database;

    public AuthenticationResource() {
        this.database = new Database();
        this.registerDefaultUsers();
        this.authenticationHandler = new ValidateNullObject();
        this.authenticationHandler
                .linkWith(new ValidateRequestLimit())
                .linkWith(new ValidateUser(database))
                .linkWith(new ValidatePassword(database));

    }

    private void registerDefaultUsers() {
        database.register("user", "userPassword");
        database.register("admin", "adminPassword");
    }

    public void doAuthentication(AuthenticationRequest authenticationRequest) {
        if (authenticationHandler.check(authenticationRequest)) {
            log.info("Authorization have been successful!");
        }
        log.error("User not logged.");
    }
}
