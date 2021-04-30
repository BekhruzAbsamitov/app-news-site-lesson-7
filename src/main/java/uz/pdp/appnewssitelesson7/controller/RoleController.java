package uz.pdp.appnewssitelesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssitelesson7.aop.CheckPermission;
import uz.pdp.appnewssitelesson7.dto.RoleDto;
import uz.pdp.appnewssitelesson7.entity.ApiResponse;
import uz.pdp.appnewssitelesson7.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_POSITION')")
    @PostMapping
    public HttpEntity<?> addRole(@RequestBody RoleDto roleDto) {
        final ApiResponse response = roleService.addRole(roleDto);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_POSITION')")
    @PutMapping("/{id}")
    @CheckPermission(value = "EDIT_POSITION")
    public HttpEntity<?> editRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        final ApiResponse response = roleService.editRole(id, roleDto);
        return ResponseEntity.status(response.isSuccess ? 201 : 409).body(response);
    }

}
