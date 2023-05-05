package uz.atmos.weatheratmos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.atmos.weatheratmos.dtos.CityDto;
import uz.atmos.weatheratmos.dtos.user.UpdateUserDto;
import uz.atmos.weatheratmos.entity.City;
import uz.atmos.weatheratmos.response.ApiResponse;
import uz.atmos.weatheratmos.service.CityService;
import uz.atmos.weatheratmos.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final CityService cityService;
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(path = "/user-list")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
//    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/user-details")
    public ResponseEntity<?> getSubscription(@RequestBody Long id){
        ResponseEntity<List<City>> ok = ResponseEntity.ok(userService.getSub(id));
        return ok;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(path = "/edit-user")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDto dto){
        ApiResponse apiResponse = userService.updateUser(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CityDto>> getAllCity(){
        return ResponseEntity.ok(cityService.getAll());
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/edit-city")
    public ResponseEntity<?> update(@Valid @RequestBody City city) {
        ApiResponse apiResponse = cityService.updateCity(city);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
