package uz.atmos.weatheratmos.dtos.user;

import lombok.*;


import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank
    private String email;
    @NotBlank(message = "Password should not be blank")
    private String password;
}
