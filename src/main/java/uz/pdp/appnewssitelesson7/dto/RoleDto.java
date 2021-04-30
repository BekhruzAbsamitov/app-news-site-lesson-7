package uz.pdp.appnewssitelesson7.dto;

import lombok.Data;
import uz.pdp.appnewssitelesson7.entity.enums.Permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleDto {

    @NotBlank
    private String name;

    private String description;

    @NotEmpty
    private List<Permission> permissionList;
}
