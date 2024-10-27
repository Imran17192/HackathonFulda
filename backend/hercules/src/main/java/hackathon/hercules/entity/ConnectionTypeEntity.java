package hackathon.hercules.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ConnectionTypeEntity extends ObjectEntity implements Serializable {


    private Long connectionType_id;

    private String name;

    public ConnectionTypeEntity() {
    }

    public ConnectionTypeEntity(Long connectionType_id, String name) {
        this.connectionType_id = connectionType_id;
        this.name = name;
    }

    public Long getConnectionType_id() {
        return connectionType_id;
    }

    public void setConnectionType_id(Long connectionType_id) {
        this.connectionType_id = connectionType_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
