package uz.atmos.weatheratmos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.atmos.weatheratmos.entity.enums.WeatherType;
import uz.atmos.weatheratmos.entity.enums.WeekDays;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private WeekDays weekDays;
    private LocalDateTime localDateTime;
    @Enumerated(value = EnumType.STRING)
    private WeatherType weatherType;
    private Integer morningTemp;
    private Integer middayTemp;
    private Integer nightTemp;
    private Integer humidity;
    private Double windSpeed;
    @OneToOne
    private City city;

}
