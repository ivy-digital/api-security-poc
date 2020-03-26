package br.com.agsi.security.poc.securitypoc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beneficiario")
public class beneficiarioController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> getInfo(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(((UserDetails)principal).getUsername());
    }
}
