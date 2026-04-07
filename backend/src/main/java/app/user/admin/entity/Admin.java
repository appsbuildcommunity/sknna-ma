package app.user.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import app.user.model.User;

@Data
@Entity
@Table(name = "admins")
public class Admin extends User {
    
}
