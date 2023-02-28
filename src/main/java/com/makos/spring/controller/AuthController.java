package com.makos.spring.controller;

import com.makos.spring.dto.AuthenticationDTO;
import com.makos.spring.dto.SecurePersonDTO;
import com.makos.spring.mapper.SecurePersonMapper;
import com.makos.spring.model.SecurePerson;
import com.makos.spring.security.JWTUtil;
import com.makos.spring.service.SecurePersonService;
import com.makos.spring.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final SecurePersonService personService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(SecurePersonService personService, PersonValidator personValidator, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());

        return Map.of("jwt-token", token);
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid SecurePersonDTO personDTO,
                                                   BindingResult bindingResult) {
        SecurePerson person = SecurePersonMapper.INSTANCE.toEntity(personDTO);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "Error");
        }

        personService.register(person);
        String token = jwtUtil.generateToken(person.getName());

        return Map.of("jwt-token", token);
    }
}
