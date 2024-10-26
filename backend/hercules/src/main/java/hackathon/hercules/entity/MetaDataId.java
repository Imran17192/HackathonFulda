package hackathon.hercules.entity;

import java.io.Serializable;

public class MetaDataId implements Serializable {
    ObjectEntity ownerObject;
    String key;

    public MetaDataId(ObjectEntity ownerObject, String key) {
        this.ownerObject = ownerObject;
        this.key = key;
    }
}
