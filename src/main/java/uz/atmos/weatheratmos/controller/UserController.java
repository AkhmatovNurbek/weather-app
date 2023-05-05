package uz.atmos.weatheratmos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import uz.atmos.weatheratmos.dtos.CityDto;
import uz.atmos.weatheratmos.entity.AuthUser;
import uz.atmos.weatheratmos.entity.City;
import uz.atmos.weatheratmos.repository.CityRepository;
import uz.atmos.weatheratmos.repository.UserRepository;
import uz.atmos.weatheratmos.response.ApiResponse;
import uz.atmos.weatheratmos.service.AuthService;
import uz.atmos.weatheratmos.service.CityService;
import uz.atmos.weatheratmos.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final AuthService authService;
    private final UserService userService;
    private final CityService cityService;

    @PutMapping(value = "/subscribe-to-city")
    public ResponseEntity<?> subscribe(@RequestBody Long cityID) {
        ApiResponse apiResponse = userService.subscribe(cityID);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/showCities")
    public ResponseEntity<List<CityDto>> getAllCity(){
        return ResponseEntity.ok(cityService.showAll());
    }


    @GetMapping(value = "/get-subscription")
    public ResponseEntity<List<CityDto>> getSubCity(){
        return ResponseEntity.ok(userService.getSubCity());
    }

}
