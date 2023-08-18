package com.jia.loan.projections.dto;

import com.jia.loan.projections.indicator.LoanTypeIndicator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class created to be the Data Transfer Object (DTO) for Loans.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @since 2023-08-18
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private int loanDuration;

    private LoanTypeIndicator loanTypeIndicator;

    private Date startDate;

    private BigDecimal amount;
}
