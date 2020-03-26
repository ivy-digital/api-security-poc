package br.com.agsi.security.poc.securitypoc.filter;

import br.com.agsi.security.poc.securitypoc.service.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AgsiAuthenticationFilter extends GenericFilterBean {

    private AuthenticationService authenticationService;

    public AgsiAuthenticationFilter(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        // Autenticação do Client e do beneficiário
        Authentication authentication = authenticationService.getAuthentication((HttpServletRequest) request);

        // Seta o usuário autenticado no contexto da aplicação
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
