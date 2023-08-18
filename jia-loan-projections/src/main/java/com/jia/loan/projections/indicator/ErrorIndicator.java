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
     * Permission errors.
     */
    FIM_ERROR_P1("You don't have permission to do this."),

    FIM_ERROR_P2("Expired token, you'll need to re-login on the system."),

    FIM_ERROR_P3("Login or password are invalid!"),

    FIM_ERROR_P4("User already has the max number of profiles!"),

    /**
     * Media errors.
     */
    FIM_ERROR_M01("Media not found or file doesn't have a request URL!"),

    /**
     * Entity "User" errors.
     */
    FIM_ERROR_U01("User not found!"),

    FIM_ERROR_U02("User is already registered, log-in instead!"),

    FIM_ERROR_U03("User is already registered but deactivated!"),

    /**
     * Entity "Plan" errors.
     */
    FIM_ERROR_PL01("Plan not found!"),

    /**
     * System errors.
     */
    FIM_ERROR_S01("Central API returned 5XX status code!"),

    FIM_ERROR_S02("Central API returned 4XX status code!"),

    FIM_ERROR_S03("Central API returned an search!"),
    ;

    String message;

    public String getMessage() {
        return message;
    }

    ErrorIndicator(String message) {
        this.message = message;
    }
}
