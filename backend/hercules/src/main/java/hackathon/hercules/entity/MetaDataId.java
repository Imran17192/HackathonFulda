package hackathon.hercules.entity;

import java.io.Serializable;

public class MetaDataId implements Serializable {
    ObjectEntity owner;
    String key;

    public MetaDataId(ObjectEntity owner, String key) {
        this.owner = owner;
        this.key = key;
    }
}
