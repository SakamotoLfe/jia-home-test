package com.jia.loan.projections.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jia.loan.projections.dto.LoanRequestDTO;
import com.jia.loan.projections.dto.LoanResponseDTO;
import com.jia.loan.projections.exception.LoanBadRequestException;
import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.indicator.LoanTypeIndicator;
import com.jia.loan.projections.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoanControllerTest {

    @InjectMocks
    private LoanController loanController;

    MockMvc mockMvc;

    @MockBean
    LoanService loanService;

    ObjectMapper objectMapper = new ObjectMapper();

    LoanRequestDTO loanRequestDTO;

    LoanResponseDTO loanResponseDTO;

    @BeforeEach
    void setUp() {
        loanService = Mockito.mock(LoanService.class);
        loanController = new LoanController(loanService);
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
        loanRequestDTO = new LoanRequestDTO();
        loanResponseDTO = new LoanResponseDTO();
    }

    @Test
    @DisplayName("whenFeeProjectionsReturnOK")
    void whenFeeProjectionsReturnOK() throws Exception {
        Mockito.when(loanService.calculateWeeklyFee(Mockito.any())).thenReturn(new HashSet<>());
        loanRequestDTO = new LoanRequestDTO(2, LoanTypeIndicator.WEEKLY, new Date(), BigDecimal.TEN);
        mockMvc.perform(MockMvcRequestBuilders.post("/loans/fee-projections")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loanRequestDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

        loanRequestDTO = new LoanRequestDTO(2, LoanTypeIndicator.MONTHLY, new Date(), BigDecimal.TEN);
        mockMvc.perform(MockMvcRequestBuilders.post("/loans/fee-projections")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loanRequestDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("whenInstallmentProjectionsReturnOK")
    void whenInstallmentProjectionsReturnOK() throws Exception {
        Mockito.when(loanService.calculateWeeklyInstallment(Mockito.any())).thenReturn(new HashSet<>());
        loanRequestDTO = new LoanRequestDTO(2, LoanTypeIndicator.WEEKLY, new Date(), BigDecimal.TEN);
        mockMvc.perform(MockMvcRequestBuilders.post("/loans/installment-projections")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loanRequestDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

        loanRequestDTO = new LoanRequestDTO(2, LoanTypeIndicator.MONTHLY, new Date(), BigDecimal.TEN);
        mockMvc.perform(MockMvcRequestBuilders.post("/loans/installment-projections")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loanRequestDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("whenFeeProjectionsReturnError")
    void whenFeeProjectionsReturnError() {
        loanRequestDTO.setLoanTypeIndicator(null);
        assertThrows(LoanBadRequestException.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders.post("/loans/fee-projections")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(loanRequestDTO))
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        });

    }

    @Test
    @DisplayName("whenInstallmentProjectionsReturnError")
    void whenInstallmentProjectionsReturnError() throws Exception {
        loanRequestDTO.setLoanTypeIndicator(null);
        assertThrows(LoanBadRequestException.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders.post("/loans/installment-projections")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(loanRequestDTO))
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        });
    }
}