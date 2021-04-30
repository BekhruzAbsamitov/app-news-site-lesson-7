package uz.pdp.appnewssitelesson7.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotNull(message = "username Field must not be empty")
    private String username;
    @NotNull(message = "password Field must not be empty")
    private String password;
}
