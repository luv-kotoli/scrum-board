package se.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.auth.model.User;
import se.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class SecurityService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public SecurityService(UserRepository repository){
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        if(user == null) {
            throw new UsernameNotFoundException("");
        }
        return user;
    }
}
