package uz.pdp.appnewssitelesson7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "fullName Field must not be empty")
    private String fullName;

    @NotNull(message = "username Field must not be empty")
    private String username;

    @NotNull(message = "password Field must not be empty")
    private String password;

    @NotNull(message = "Role ID must not be empty")
    private Integer roleId;

}
