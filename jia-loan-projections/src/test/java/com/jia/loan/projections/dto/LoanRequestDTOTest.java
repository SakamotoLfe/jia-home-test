package com.jia.loan.projections.dto;

import com.jia.loan.projections.indicator.LoanTypeIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoanRequestDTOTest {

    @InjectMocks
    private LoanRequestDTO loanRequestDTO;

    @BeforeEach
    void setUp() {
        loanRequestDTO = new LoanRequestDTO();
    }

    @Test
    @DisplayName("getLoanDuration")
    void getLoanDuration() {
        assertDoesNotThrow(() -> loanRequestDTO.getLoanDuration());
    }

    @Test
    @DisplayName("getLoanTypeIndicator")
    void getLoanTypeIndicator() {
        assertDoesNotThrow(() -> loanRequestDTO.getLoanTypeIndicator());
    }

    @Test
    @DisplayName("getStartDate")
    void getStartDate() {
        assertDoesNotThrow(() -> loanRequestDTO.getStartDate());
    }

    @Test
    @DisplayName("getAmount")
    void getAmount() {
        assertDoesNotThrow(() -> loanRequestDTO.getAmount());
    }

    @Test
    @DisplayName("setLoanDuration")
    void setLoanDuration() {
        assertDoesNotThrow(() -> loanRequestDTO.setLoanDuration(5));
    }

    @Test
    @DisplayName("setLoanTypeIndicator")
    void setLoanTypeIndicator() {
        assertDoesNotThrow(() -> loanRequestDTO.setLoanTypeIndicator(LoanTypeIndicator.WEEKLY));
    }

    @Test
    @DisplayName("setStartDate")
    void setStartDate() {
        assertDoesNotThrow(() -> loanRequestDTO.setStartDate(new Date()));
    }

    @Test
    @DisplayName("setAmount")
    void setAmount() {
        assertDoesNotThrow(() -> loanRequestDTO.setAmount(BigDecimal.TEN));
    }

    @Test
    @DisplayName("testToString")
    void testToString() {
        assertDoesNotThrow(() -> loanRequestDTO.toString());
    }
}