package uz.atmos.weatheratmos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.atmos.weatheratmos.dtos.CityDto;
import uz.atmos.weatheratmos.entity.City;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto toDto(City city);
    City toEntity(CityDto dto);
}
