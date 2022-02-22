package edu.my.app.repositories;

import edu.my.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUsername(String name);
}
