package com.jia.loan.projections.exception;

import java.io.IOException;

/**
 * Class created to be an exception related to Authorizations and permissions.
 *
 * @author Alfredo Marin
 * @version BETA-1.0
 * @since 2023-08-18
 */

public class LoanNotFoundException extends IOException {

    /**
     * Constructor.
     *
     * @param message Message of the exception.
     */
    public LoanNotFoundException(String message) {
        super(message);
        /**
         * Message of the exception.
         */
    }
}
