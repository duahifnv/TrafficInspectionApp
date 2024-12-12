package dstu.inspection.service;

import dstu.inspection.dto.UserDto;
import dstu.inspection.entity.security.Role;
import dstu.inspection.entity.security.User;
import dstu.inspection.repository.security.UserRepository;
import dstu.inspection.validation.UserAlreadyExistAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByPhone(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with phone '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(),
                user.getPassword(), rolesToAuthorities(user.getRoles()));
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
    public User findByEmail(String email) {
        return userRepository.findByPhone(email);
    }
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
    }
    @Transactional
    public User save(UserDto userDto)
            throws UserAlreadyExistAuthenticationException {
        if (emailExists(userDto.getEmail()) || phoneExists(userDto.getPhone())) {
            throw new UserAlreadyExistAuthenticationException(
                    "Пользователь с таким номер телефона или почтой уже существует");
        }
        Role userDefaultRole = Role.builder()
                .roleId(2L)
                .name("ROLE_USER")
                .build();
        List<Role> userRoles = Arrays.asList(userDefaultRole);
        String preparedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = User.builder()
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .password(preparedPassword)
                .roles(userRoles)
                .build();
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        if (userRepository.findById(user.getUserId()).isPresent()) {
            return userRepository.save(user);
        }
        return null;
    }
    public void deleteUser(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user != null) {
            userRepository.deleteById(user.getUserId());
        }
    }
    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    private boolean phoneExists(String phone) {
        return userRepository.findByPhone(phone) != null;
    }
}
