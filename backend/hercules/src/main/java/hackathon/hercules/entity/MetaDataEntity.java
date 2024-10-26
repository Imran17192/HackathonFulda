package hackathon.hercules.entity;

import jakarta.persistence.*;

@Entity
@IdClass(MetaDataEntity.class)
public class MetaDataEntity extends ObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectEntity ownerObject;
    @Id
    private String key;

    private String value;

    public MetaDataEntity() {
    }

    public MetaDataEntity(ObjectEntity ownerObject, String key, String value) {
        this.ownerObject = ownerObject;
        this.key = key;
        this.value = value;
    }

    public ObjectEntity getOwnerObject() {
        return ownerObject;
    }

    public void setOwnerObject(ObjectEntity ownerObject) {
        this.ownerObject = ownerObject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

