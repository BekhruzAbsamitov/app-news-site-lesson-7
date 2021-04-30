package uz.pdp.appnewssitelesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitelesson7.dto.RoleDto;
import uz.pdp.appnewssitelesson7.entity.ApiResponse;
import uz.pdp.appnewssitelesson7.entity.Role;
import uz.pdp.appnewssitelesson7.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {

        if (roleRepository.existsByName(roleDto.getName())) {
            return new ApiResponse("Role already exists", false);
        }

        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermissionList(),
                roleDto.getDescription()
        );
        final Role save = roleRepository.save(role);
        return new ApiResponse("Role added", true, save);
    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {
        return new ApiResponse("", true);
    }
}
