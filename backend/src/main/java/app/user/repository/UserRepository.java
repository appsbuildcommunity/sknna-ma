package app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.user.model.User;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
        boolean existsByEmail(String email);
        User findByEmail(String email);
}
