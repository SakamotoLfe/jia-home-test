package com.jia.loan.projections.indicator;

/**
 * Enum created to be an indicator of ErrorIndicator.
 *
 * @author Alfredo marin
 * @version ALPHA-0.0.1
 * @see ErrorIndicator
 * @since 2023-02-19
 */

public enum ErrorIndicator {

    /**
     * Date Limit errors.
     */
    JLP_DL_01("The API doesn't support the loan duration received, please use the supported amount (1-4 weeks or 1-12 months)."),

    /**
     * Entity not found errors.
     */
    JLP_NF_01("The API couldn't locate the entity that you're looking for."),

    /**
     * Bad Request errors.
     */
    JLP_BR_01("The loan type received is not valid!"),
    ;

    final String message;

    public String getMessage() {
        return message;
    }

    ErrorIndicator(String message) {
        this.message = message;
    }
}
