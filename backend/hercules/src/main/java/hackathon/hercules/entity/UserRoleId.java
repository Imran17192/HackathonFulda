package hackathon.hercules.entity;

import java.io.Serializable;

public class UserRoleId implements Serializable {
    UserEntity user;
    RoleEntity role;

    public UserRoleId(UserEntity user, RoleEntity key) {
        this.user = user;
        this.role = key;
    }
}
