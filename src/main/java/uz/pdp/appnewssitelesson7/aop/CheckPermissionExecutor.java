package uz.pdp.appnewssitelesson7.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssitelesson7.entity.User;
import uz.pdp.appnewssitelesson7.exceptions.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermission(CheckPermission checkPermission) {
//checkPermission.value()
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exists = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new ForbiddenException(checkPermission.value(), "Not Allowed!");
        }
    }

}
