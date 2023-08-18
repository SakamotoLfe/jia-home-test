package com.jia.loan.projections.config;

import com.annerd.commons.utilities.exception.RestRequestException;
import com.annerd.findme.exception.AuthorizationException;
import com.annerd.findme.exception.FindMePlanLimitationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Class created to hold the exception handling for Rest Requests.
 *
 * @author Alfredo Marin
 * @version BETA-1.5.0
 * @since 2022-06-27
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class WebRestControllerAdvisor extends OncePerRequestFilter {

    /**
     * Handler that will handle Rest Request Exceptions.
     *
     * @param e Exception thrown.
     * @return Exception to be returned.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestRequestException.class)
    public RestRequestException handleRestRequestException(RestRequestException e) {
        return e;
    }

    /**
     * Handler that will handle Authorization Exceptions.
     *
     * @param e Exception thrown.
     * @return Exception to be returned.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public AuthorizationException handleAuthorizationException(AuthorizationException e) {
        return e;
    }

    /**
     * Handler that will handle Coupler Plan Limitations Exceptions.
     *
     * @param e Exception thrown.
     * @return Exception to be returned.
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(FindMePlanLimitationException.class)
    public FindMePlanLimitationException handleCouplerPlanLimitationException(FindMePlanLimitationException e) {
        return e;
    }

    /**
     * Method that filter the requests and handle the operation.
     *
     * @param request     Request to be processed.
     * @param response    Response to be returned.
     * @param filterChain Filter chain of the application.
     * @throws ServletException It can encounter an unknown response or node on the chain.
     * @throws IOException      The chain can have a problem or encounter an unknown input/output.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }
}
