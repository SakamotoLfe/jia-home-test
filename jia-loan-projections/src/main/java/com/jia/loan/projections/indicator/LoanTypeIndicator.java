package com.jia.loan.projections.indicator;

import lombok.Getter;

/**
 * Enum created to be an indicator of LoanTypeIndicator.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @see LoanTypeIndicator
 * @since 2023-08-18
 */

public enum LoanTypeIndicator {

    WEEKLY("Weekly", 1, 4),

    MONTHLY("Monthly", 1, 12),
    ;

    final String loanType;

    @Getter
    final int minDuration;

    @Getter
    final int maxDuration;

    LoanTypeIndicator(String loanType, int minDuration, int maxDuration) {
        this.loanType = loanType;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
    }
}
