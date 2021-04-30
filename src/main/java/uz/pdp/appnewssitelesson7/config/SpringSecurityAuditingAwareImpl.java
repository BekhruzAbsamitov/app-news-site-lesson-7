package uz.pdp.appnewssitelesson7.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.appnewssitelesson7.entity.User;

import java.util.Optional;

public class SpringSecurityAuditingAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            final User user = (User) authentication.getPrincipal();

            return Optional.of(user.getId());
        }
        return Optional.empty();
    }
}
