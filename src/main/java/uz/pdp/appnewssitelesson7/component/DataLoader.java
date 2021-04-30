package uz.pdp.appnewssitelesson7.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssitelesson7.entity.Role;
import uz.pdp.appnewssitelesson7.entity.User;
import uz.pdp.appnewssitelesson7.entity.enums.Permission;
import uz.pdp.appnewssitelesson7.repository.RoleRepository;
import uz.pdp.appnewssitelesson7.repository.UserRepository;
import uz.pdp.appnewssitelesson7.utils.AppConstants;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            final Permission[] values = Permission.values();
            final Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(values),
                    "admin"
            ));
            final Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(Permission.ADD_COMMENT, Permission.EDIT_COMMENT, Permission.DELETE_MY_COMMENT),
                    "Simple user"
            ));

            userRepository.save(new User(
                    "Admin", "admin", passwordEncoder.encode("admin"), admin, true
            ));

            userRepository.save(new User(
                    "User", "user", passwordEncoder.encode("user"), user, true
            ));
        }
    }
}