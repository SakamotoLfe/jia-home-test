package com.jia.loan.projections.exception;

import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * Class created to be an exception related to Authorizations and permissions.
 *
 * @author Alfredo Marin
 * @version BETA-1.12.0
 * @since 2022-10-10
 */

public class AuthorizationException extends IOException {

    /**
     * Status code of the exception.
     */
    private final Integer statusCode;

    /**
     * Message of the exception.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param statusCode Status code of the exception.
     * @param message    Message of the exception.
     */
    public AuthorizationException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Constructor.
     *
     * @param statusCode Status code of the exception.
     * @param message    Message of the exception.
     */
    public AuthorizationException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode.value();
        this.message = message;
    }
}
