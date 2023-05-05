package uz.atmos.weatheratmos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atmos.weatheratmos.dtos.user.AuthenticationRequest;
import uz.atmos.weatheratmos.dtos.user.RegisterRequest;
import uz.atmos.weatheratmos.response.AuthenticationResponse;
import uz.atmos.weatheratmos.service.AuthService;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }



}
