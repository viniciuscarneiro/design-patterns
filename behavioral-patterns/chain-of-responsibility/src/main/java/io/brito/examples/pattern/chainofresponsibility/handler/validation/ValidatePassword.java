package io.brito.examples.pattern.chainofresponsibility.handler.validation;

import io.brito.examples.pattern.chainofresponsibility.resource.model.AuthenticationRequest;
import io.brito.examples.pattern.chainofresponsibility.handler.AuthenticationHandler;
import io.brito.examples.pattern.chainofresponsibility.util.Database;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidatePassword extends AuthenticationHandler {

    private Database database;

    public ValidatePassword(Database database) {
        this.database = database;
    }

    @Override
    public boolean check(AuthenticationRequest authenticationRequest) {
        if(this.database.isValidPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword())){
            log.info("Is a valid password.");
            return checkNext(authenticationRequest);
        }
        log.error("Is a invalid password");
        return false;
    }
}
