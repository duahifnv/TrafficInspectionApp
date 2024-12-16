package dstu.inspection.service;

import dstu.inspection.dto.user.UserDto;
import dstu.inspection.entity.security.Role;
import dstu.inspection.entity.security.User;
import dstu.inspection.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with username '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), rolesToAuthorities(user.getRoles()));
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
    }
    @Transactional
    public User save(UserDto userDto)
            throws UserAlreadyExistAuthenticationException {
        if (emailExists(userDto.getEmail()) || usernameExists(userDto.getUsername())) {
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
                .username(userDto.getUsername())
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
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.deleteById(user.getUserId());
        }
    }
    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
