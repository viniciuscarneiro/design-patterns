package io.brito.examples.pattern.chainofresponsibility.handler.validation;

import io.brito.examples.pattern.chainofresponsibility.handler.AuthenticationHandler;
import io.brito.examples.pattern.chainofresponsibility.resource.model.AuthenticationRequest;
import io.brito.examples.pattern.chainofresponsibility.util.Database;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateUser extends AuthenticationHandler {

    private Database database;

    public ValidateUser(Database database) {
        this.database = database;
    }

    @Override
    public boolean check(AuthenticationRequest authenticationRequest) {
        if (this.database.hasUsername(authenticationRequest.getUsername())) {
            log.info("Is a valid user [{}] ", authenticationRequest.getUsername());
            return checkNext(authenticationRequest);
        }
        log.error("Is a invalid user [{}]", authenticationRequest.getUsername());
        return false;
    }
}
