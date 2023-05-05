package uz.atmos.weatheratmos.dtos;


import lombok.*;
import uz.atmos.weatheratmos.entity.enums.WeatherType;
import uz.atmos.weatheratmos.entity.enums.WeekDays;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {
    private String name;
    private WeekDays weekDays;
    private LocalDateTime localDateTime;
    private WeatherType weatherType;
    private Integer morningTemp;
    private Integer middayTemp;
    private Integer nightTemp;
    private Integer humidity;
    private Double windSpeed;

}
