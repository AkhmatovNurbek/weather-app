package uz.atmos.weatheratmos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.atmos.weatheratmos.entity.enums.WeekDays;
import uz.atmos.weatheratmos.entity.enums.WeatherType;
import uz.atmos.weatheratmos.entity.template.AbsMainEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City extends AbsMainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer state = 1;

}