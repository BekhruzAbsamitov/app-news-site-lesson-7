package uz.pdp.appnewssitelesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitelesson7.exceptions.ResourceNotFoundException;
import uz.pdp.appnewssitelesson7.repository.RoleRepository;
import uz.pdp.appnewssitelesson7.repository.UserRepository;
import uz.pdp.appnewssitelesson7.dto.RegisterDto;
import uz.pdp.appnewssitelesson7.entity.ApiResponse;
import uz.pdp.appnewssitelesson7.entity.User;
import uz.pdp.appnewssitelesson7.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Passwords don't match", false);

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ApiResponse("Username already exists", false);
        }

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() ->
                        new ResourceNotFoundException("Role", "Name", AppConstants.USER))
                , true);

        userRepository.save(user);
        return new ApiResponse("Successfully registered!", true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
