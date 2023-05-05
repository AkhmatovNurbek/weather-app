package uz.atmos.weatheratmos.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.atmos.weatheratmos.dtos.CityDto;
import uz.atmos.weatheratmos.entity.City;
import uz.atmos.weatheratmos.exceptions.GenericNotFoundException;
import uz.atmos.weatheratmos.repository.CityRepository;
import uz.atmos.weatheratmos.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    public List<CityDto> getAll() {
        List<CityDto> cityDtos = new ArrayList<>();
        cityRepository.getAll().orElseThrow(()->{
            throw new GenericNotFoundException("City Not Found",404);
        }).forEach(city -> cityDtos.add(this.getDto(city.getId())));
        return cityDtos;
    }
    public CityDto getDto(@NonNull Long id){
        City city = cityRepository.get(id).orElseThrow(() -> new GenericNotFoundException("Not Found", 404));
        CityDto cityDto = new CityDto();
        cityDto.setName(cityDto.getName());
        cityDto.setWindSpeed(cityDto.getWindSpeed());
        return  cityDto;
    }

    public ApiResponse updateCity(City city) {
        City oneCity = getOne(city.getId());
        oneCity.setState(city.getState());
        oneCity.setName(city.getName());
        cityRepository.save(oneCity);
        return new ApiResponse("City updated", true ,200);
    }

    public City getOne(@NonNull Long id ){
        return  cityRepository.findById(id).
                orElseThrow(()-> new GenericNotFoundException("Company not found" , 404));
    }

    public List<CityDto> showAll() {
        List<CityDto> cityDtos = new ArrayList<>();
        cityRepository.showAll().orElseThrow(()->{
            throw new GenericNotFoundException("City Not Found",404);
        }).forEach(city -> cityDtos.add(this.getDto(city.getId())));
        return cityDtos;

    }
}
