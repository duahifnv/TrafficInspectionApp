package dstu.inspection.service;

import dstu.inspection.entity.security.Role;
import dstu.inspection.entity.security.User;
import dstu.inspection.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
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
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
    public User findByEmail(String email) {
        return userRepository.findByPhone(email);
    }
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
    }
}
