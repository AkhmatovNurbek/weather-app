package uz.atmos.weatheratmos.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    @NotNull
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, max = 40)
    private String password;
}
