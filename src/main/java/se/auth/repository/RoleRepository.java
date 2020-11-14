package se.auth.repository;

import se.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByRolename(String name);
}