package uz.atmos.weatheratmos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.atmos.weatheratmos.dtos.user.AuthenticationRequest;
import uz.atmos.weatheratmos.dtos.user.RegisterRequest;
import uz.atmos.weatheratmos.entity.AuthUser;
import uz.atmos.weatheratmos.entity.enums.MyRole;

import uz.atmos.weatheratmos.jwt.JwtService;
import uz.atmos.weatheratmos.repository.UserRepository;
import uz.atmos.weatheratmos.response.AuthenticationResponse;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            return AuthenticationResponse.builder().message("This email already exists").build();
        }
        var user = AuthUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(MyRole.ROLE_USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse  authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
    }


}
