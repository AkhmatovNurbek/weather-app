package uz.atmos.weatheratmos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.atmos.weatheratmos.dtos.CityDto;
import uz.atmos.weatheratmos.dtos.user.UpdateUserDto;
import uz.atmos.weatheratmos.entity.AuthUser;
import uz.atmos.weatheratmos.entity.City;
import uz.atmos.weatheratmos.exceptions.GenericNotFoundException;
import uz.atmos.weatheratmos.mapper.CityMapper;
import uz.atmos.weatheratmos.repository.CityRepository;
import uz.atmos.weatheratmos.repository.UserRepository;
import uz.atmos.weatheratmos.response.ApiResponse;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CityRepository cityRepository;

    public List<AuthUser> getAll() {
        List<AuthUser> all = userRepository.findAll();
        return all;
    }

    public List<City> getSub(Long id) {
        List<City> cities1 = userRepository.findAllByCities(id).orElseThrow(() -> new GenericNotFoundException("Not Found", 404));
        ArrayList<City> cities = new ArrayList<>(cities1);
        return cities;
    }

    public ApiResponse updateUser(UpdateUserDto dto) {
        Optional<AuthUser> optionalAuthUser = userRepository.findByEmail(dto.getEmail());
        AuthUser authUser = optionalAuthUser.get();
        authUser.setUserRole(dto.getRole());
        authUser.setEmail(dto.getEmail());
        userRepository.save(authUser);
        return new ApiResponse("Successfully updated", true , 204);
    }

    public ApiResponse subscribe(Long id) {
        Set<City> citySet = new HashSet<>();
        AuthUser currentUser = authService.getCurrentUser();
        Optional<City> city = cityRepository.findById(id);
        City city1 = city.get();
        for (City currentUserCity : currentUser.getCities()) {
            citySet.add(currentUserCity);
        }
        citySet.add(city1);
        currentUser.setCities(citySet);
        return new ApiResponse("City added to your city list",true,204);
    }

    public List<CityDto> getSubCity() {
        List<CityDto> cityList = new ArrayList<>();
        AuthUser currentUser = authService.getCurrentUser();
        Set<City> cities = currentUser.getCities();
        for (City city : cities) {
            cityList.add(CityMapper.INSTANCE.toDto(city));
        }
        return cityList;
    }
}
