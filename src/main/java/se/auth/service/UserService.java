package se.auth.service;

import org.springframework.stereotype.Service;
import se.auth.model.User;
import org.springframework.data.domain.Page;


public interface UserService {
    void save(User user);
}
