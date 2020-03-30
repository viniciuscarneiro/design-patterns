package io.brito.examples.pattern.chainofresponsibility.handler;

import io.brito.examples.pattern.chainofresponsibility.resource.model.AuthenticationRequest;
import lombok.Getter;

public abstract class AuthenticationHandler {

    @Getter
    private AuthenticationHandler next;

    public AuthenticationHandler linkWith(AuthenticationHandler next) {
        this.next = next;
        return next;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     * @param authenticationRequest
     * @return
     */
    public abstract boolean check(AuthenticationRequest authenticationRequest);

    /**
     * Runs check on the next object in chain or ends traversing if we're in last object in chain.
     * @param authenticationRequest
     * @return
     */
    protected boolean checkNext(AuthenticationRequest authenticationRequest) {
        if (next == null) {
            return true;
        }
        return next.check(authenticationRequest);
    }
}
