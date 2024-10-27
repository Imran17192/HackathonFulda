package hackathon.hercules.entity;

import jakarta.persistence.*;

@Entity

public class UserRoleEntity extends ObjectEntity{

    private UserEntity user;

    private RoleEntity role;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserEntity user, RoleEntity role) {
        this.user = user;
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
