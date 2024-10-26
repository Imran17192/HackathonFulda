package hackathon.hercules.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ObjectEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long object_id;

    private ServerEntity server;

    public ObjectEntity() {
    }

    public ObjectEntity(Long object_id, ServerEntity server) {
        this.object_id = object_id;
        this.server = server;
    }

    public Long getObject_id() {
        return object_id;
    }

    public void setObject_id(Long object_id) {
        this.object_id = object_id;
    }

    public ServerEntity getServer() {
        return server;
    }

    public void setServer(ServerEntity server) {
        this.server = server;
    }
}