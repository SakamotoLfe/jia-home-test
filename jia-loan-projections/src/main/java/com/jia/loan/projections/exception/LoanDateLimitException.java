package com.jia.loan.projections.exception;

import java.io.IOException;

/**
 * Class created to be an exception related to Authorizations and permissions.
 *
 * @author Alfredo Marin
 * @version BETA-1.0
 * @since 2022-10-10
 */

public class LoanDateLimitException extends IOException {

    /**
     * Constructor.
     *
     * @param message Message of the exception.
     */
    public LoanDateLimitException(String message) {
        super(message);
        /**
         * Message of the exception.
         */
    }
}
