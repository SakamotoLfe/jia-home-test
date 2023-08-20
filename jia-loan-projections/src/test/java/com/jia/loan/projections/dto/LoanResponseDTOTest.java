package com.jia.loan.projections.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoanResponseDTOTest {

    @InjectMocks
    private LoanResponseDTO loanResponseDTO;

    @BeforeEach
    void setUp() {
        loanResponseDTO = new LoanResponseDTO();
    }

    @Test
    @DisplayName("getDate")
    void getDate() {
        assertDoesNotThrow(() -> loanResponseDTO.getDate());
    }

    @Test
    @DisplayName("getAmount")
    void getAmount() {
        assertDoesNotThrow(() -> loanResponseDTO.getAmount());
    }

    @Test
    @DisplayName("setDate")
    void setDate() {
        assertDoesNotThrow(() -> loanResponseDTO.setDate(new Date()));
    }

    @Test
    @DisplayName("setAmount")
    void setAmount() {
        assertDoesNotThrow(() -> loanResponseDTO.setAmount(BigDecimal.TEN));
    }

    @Test
    @DisplayName("testToString")
    void testToString() {
        assertDoesNotThrow(() -> loanResponseDTO.toString());
    }
}