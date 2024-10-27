package hackathon.hercules.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FileEntity extends ObjectEntity{
    private Long file_id;

    private String path;

    private Long version;

    public FileEntity() {
    }

    public FileEntity(Long file_id, String path, Long version) {
        this.file_id = file_id;
        this.path = path;
        this.version = version;
    }

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
