package hackathon.hercules.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PostRoleEntity extends ObjectEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;
    @Id
    RoleEntity role;
    private Boolean read;
    private Boolean write;
    private  Boolean delete;

    public PostRoleEntity() {
    }

    public PostRoleEntity(Long post_id, RoleEntity role, Boolean read, Boolean write, Boolean delete) {
        this.post_id = post_id;
        this.role = role;
        this.read = read;
        this.write = write;
        this.delete = delete;
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
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
