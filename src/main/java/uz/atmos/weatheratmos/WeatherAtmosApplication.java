package uz.atmos.weatheratmos;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SecurityScheme(name = "Weather Bearer", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SpringBootApplication
public class WeatherAtmosApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAtmosApplication.class, args);
    }

}
