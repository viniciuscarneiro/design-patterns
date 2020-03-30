package io.brito.examples.pattern.chainofresponsibility.handler.validation;

import io.brito.examples.pattern.chainofresponsibility.resource.model.AuthenticationRequest;
import io.brito.examples.pattern.chainofresponsibility.handler.AuthenticationHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateNullObject extends AuthenticationHandler {

    @Override
    public boolean check(AuthenticationRequest authenticationRequest) {
        if(authenticationRequest == null){
            log.error("Authentication request must no be null.");
            return false;
        }
        log.info("Authentication request is valid.");
        return checkNext(authenticationRequest);
    }
}
