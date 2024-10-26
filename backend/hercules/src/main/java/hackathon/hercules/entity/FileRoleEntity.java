package hackathon.hercules.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FileRoleEntity extends ObjectEntity {

    private FileEntity file;

    private RoleEntity role;

    private Boolean read;

    private Boolean write;

    private Boolean deleteRight;

    public FileRoleEntity() {
    }

    public FileRoleEntity(FileEntity file, Boolean deleteRight, Boolean write, Boolean read, RoleEntity role) {
        this.file = file;
        this.deleteRight = deleteRight;
        this.write = write;
        this.read = read;
        this.role = role;
    }

    public FileEntity getFile() {
        return file;
    }

    public void setFile(FileEntity file) {
        this.file = file;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public Boolean getDelete() { return deleteRight; }

    public void setDelete(Boolean deleteRight) {
        this.deleteRight = deleteRight;
    }
}
