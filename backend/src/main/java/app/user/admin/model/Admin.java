package app.user.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import app.user.model.User;

@Data
@Entity
@SuperBuilder
@Table(name = "admins")
public class Admin extends User {
    
}
