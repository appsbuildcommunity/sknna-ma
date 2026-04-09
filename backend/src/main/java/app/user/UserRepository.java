package app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import app.user.model.User;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    
}
