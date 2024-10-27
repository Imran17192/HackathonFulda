package hackathon.hercules.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class ObjectEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long object_id;

    private ServerEntity server;

    public ObjectEntity() {
    }

    public ObjectEntity(Long object_id) {
        this.object_id = object_id;
    }

    public ObjectEntity(Long object_id, ServerEntity server) {
        this.object_id = object_id;
        this.server = server;
    }

    public Long getId() {
        return object_id;
    }

    public void setId(Long object_id) {
        this.object_id = object_id;
    }

    public ServerEntity getServer() {
        return server;
    }

    public void setServer(ServerEntity server) {
        this.server = server;
    }

    public MetaDataEntity createMetaData(String key, String value) {
        return new MetaDataEntity(this, key, value);
    }

    public ConnectionEntity createConnection(ObjectEntity other, ConnectionTypeEntity t) {
        ConnectionEntity c = new ConnectionEntity();
        c.setLeftObject(this);
        c.setRightObject(other);
        c.setConnectionType(t);
        return c;
    }
}
