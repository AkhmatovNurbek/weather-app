package uz.atmos.weatheratmos.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.atmos.weatheratmos.entity.enums.MyRole;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    @NotBlank(message = "Email cannot be blank!")
    @NotNull(message = "Email can not be null!")
    private String email;

    @NotBlank(message = "Telephone cannot be blank!")
    @NotNull(message = "Telephone can not be null!")
    private MyRole role;
}
