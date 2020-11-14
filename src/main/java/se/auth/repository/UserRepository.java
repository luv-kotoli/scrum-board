package se.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.auth.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
