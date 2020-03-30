package io.brito.examples.pattern.chainofresponsibility.handler.validation;

import io.brito.examples.pattern.chainofresponsibility.resource.model.AuthenticationRequest;
import io.brito.examples.pattern.chainofresponsibility.handler.AuthenticationHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateRequestLimit extends AuthenticationHandler {

    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ValidateRequestLimit() {
        this.requestPerMinute = 2;
        this.currentTime = System.currentTimeMillis();
    }

    @Override
    public boolean check(AuthenticationRequest authenticationRequest) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }
        request++;
        if (request > requestPerMinute) {
            log.error("Request limit exceeded!");
            return false;
        }
        log.info("Request limit ok!");
        return  checkNext(authenticationRequest);
    }

}
