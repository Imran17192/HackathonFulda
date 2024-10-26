package hackathon.hercules.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConnectionEntity extends ObjectEntity {


    private Long connection_id;

    private ObjectEntity leftObject;

    private ObjectEntity rightObject;

    private ConnectionTypeEntity connectionType;

    public ConnectionEntity() {
    }

    public ConnectionEntity(Long connection_id, ObjectEntity leftObject, ObjectEntity rightObject, ConnectionTypeEntity connectionType) {
        this.connection_id = connection_id;
        this.leftObject = leftObject;
        this.rightObject = rightObject;
        this.connectionType = connectionType;
    }

    public Long getConnection_id() {
        return connection_id;
    }

    public void setConnection_id(Long connection_id) {
        this.connection_id = connection_id;
    }

    public ObjectEntity getLeftObject() {
        return leftObject;
    }

    public void setLeftObject(ObjectEntity leftObject) {
        this.leftObject = leftObject;
    }

    public ObjectEntity getRightObject() {
        return rightObject;
    }

    public void setRightObject(ObjectEntity rightObject) {
        this.rightObject = rightObject;
    }

    public ConnectionTypeEntity getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionTypeEntity connectionType) {
        this.connectionType = connectionType;
    }
}
