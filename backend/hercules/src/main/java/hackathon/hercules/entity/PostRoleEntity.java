package hackathon.hercules.entity;

import jakarta.persistence.*;

@Entity
public class PostRoleEntity extends ObjectEntity{


    private Long post_id;

    RoleEntity role;
    private Boolean read;
    private Boolean write;
    private  Boolean deleteRight;

    public PostRoleEntity() {
    }

    public PostRoleEntity(Long post_id, RoleEntity role, Boolean read, Boolean write, Boolean deleteRight) {
        this.post_id = post_id;
        this.role = role;
        this.read = read;
        this.write = write;
        this.deleteRight = deleteRight;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getDelete() {
        return deleteRight;
    }

    public void setDelete(Boolean deleteRight) {
        this.deleteRight = deleteRight;
    }
}