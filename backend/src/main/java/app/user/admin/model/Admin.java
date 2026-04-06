package app.user.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Admin extends app.user.model.User {
    
}
