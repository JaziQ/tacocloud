package jaq.tacocloud.security;

import jaq.tacocloud.User;
import jaq.tacocloud.data.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;


@Service
public class UserRepoUserDetailsService implements UserDetailsService{

    private UserRepo userRepo;
    @Autowired
    public UserRepoUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalStateException("User password is empty for " + username);
        }
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
}