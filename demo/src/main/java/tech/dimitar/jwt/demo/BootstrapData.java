package tech.dimitar.jwt.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tech.dimitar.jwt.demo.data.UserInfo;
import tech.dimitar.jwt.demo.data.UserRepository;
import tech.dimitar.jwt.demo.data.UserRole;
import tech.dimitar.jwt.demo.data.UserRoleRepository;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(" **************** BootstrapData **************** ");
        final UserRole userRole = userRoleRepository.save(
                UserRole.builder()
                        .name("USER")
                        .build()
        );
        final UserRole adminRole = userRoleRepository.save(
                UserRole.builder()
                        .name("ADMIN")
                        .build()
        );
        userRepository.save(
                UserInfo.builder()
                        .username("user")
                        .password(passwordEncoder.encode("user"))
                        .roles(Collections.singleton(userRole))
                        .build()
        );
        userRepository.save(
                UserInfo.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(Collections.singleton(adminRole))
                        .build()
        );
    }
}