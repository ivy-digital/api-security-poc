package br.com.agsi.security.poc.securitypoc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
public class AuthenticationService {

    @Autowired
    private UserDetailsService userDetailsService;

    public Authentication getAuthentication(HttpServletRequest request) {
        String clientId = request.getHeader("client_id"); // token do client que esta fazendo a requisição à api
        String userId = request.getHeader("user_id"); // numero da carteirinha, cpf, informação de identificação do usuário para quem a request esta sendo feita

        if (!StringUtils.isEmpty(userId) && externalClientAuthentication(clientId)) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(userId); // faz a busca pelo username, nesse ponto pode ser feito qualquer outra valicação do beneficiário
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), Collections.emptyList()); // cria autenticação para o usuário valido
        }
        return null;
    }

    /**
      * Método responsável pela validação do cliente externo que esta fazendo acesso à api
      * @param clientId
     * @return
     */
    private boolean externalClientAuthentication(String clientId) {

        String storedClientId = "06ddb46f-e04d-479a-941c-ce6c2d488afb"; // haviamos conversado dessa informação ficar armazenada na tabela de parametros

        return clientId.equals(storedClientId);
    }
}
