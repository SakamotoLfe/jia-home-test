package com.jia.loan.projections.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class created to be the Data Transfer Object for Fee.
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
public class LoanResponseDTO {

    @JsonProperty("date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @JsonProperty("amount")
    private BigDecimal amount;
}
