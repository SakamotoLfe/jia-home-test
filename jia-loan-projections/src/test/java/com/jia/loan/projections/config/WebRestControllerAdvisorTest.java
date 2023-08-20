package com.jia.loan.projections.config;

import com.jia.loan.projections.exception.LoanBadRequestException;
import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.exception.LoanNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WebRestControllerAdvisorTest {

    @InjectMocks
    private WebRestControllerAdvisor webRestControllerAdvisor;

    @MockBean
    HttpServletRequest request;

    @MockBean
    HttpServletResponse response;

    @MockBean
    FilterChain filterChain;

    @BeforeEach
    void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        filterChain = Mockito.mock(FilterChain.class);
        webRestControllerAdvisor = new WebRestControllerAdvisor();
    }

    @Test
    @DisplayName("whenHandleLoanDateLimitExceptionReturnOK")
    void whenHandleLoanDateLimitExceptionReturnOK() {
        assertDoesNotThrow(() -> webRestControllerAdvisor.handleLoanDateLimitException(new LoanDateLimitException("Testing!")));
    }

    @Test
    @DisplayName("whenTestHandleLoanBadRequestExceptionReturnOK")
    void whenTestHandleLoanBadRequestExceptionReturnOK() {
        assertDoesNotThrow(() -> webRestControllerAdvisor.handleLoanBadRequestException(new LoanBadRequestException("Testing!")));
    }

    @Test
    @DisplayName("whenHandleLoanNotFoundExceptionReturnOK")
    void whenHandleLoanNotFoundExceptionReturnOK() {
        assertDoesNotThrow(() -> webRestControllerAdvisor.handleLoanNotFoundException(new LoanNotFoundException("Testing!")));
    }

    @Test
    @DisplayName("whenDoFilterInternalReturnOK")
    void whenDoFilterInternalReturnOK() {
        assertDoesNotThrow(() -> webRestControllerAdvisor.doFilterInternal(request, response, filterChain));
    }
}