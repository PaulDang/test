package fr.training.service;

import fr.training.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repo.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("Cannot found "+ username + " on the DB");
//        }
        return new CustomUserDetails(null);
    }
}
