package hackathon.hercules.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class PostRoleId implements Serializable {
    UserEntity user;
    RoleEntity role;

    public PostRoleId(UserEntity user, RoleEntity role) {
        this.user = user;
        this.role = role;
    }
}
