package hackathon.hercules.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class PostRoleId implements Serializable {
    Long post_id;
    RoleEntity role;

    public PostRoleId(Long post_id, RoleEntity role) {
        this.post_id = post_id;
        this.role = role;
    }
}
