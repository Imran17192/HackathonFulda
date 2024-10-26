package hackathon.hercules.entity;

import java.io.Serializable;

public class UserRoleId implements Serializable {
    ObjectEntity user;
    String key;

    public UserRoleId(ObjectEntity user, String key) {
        this.user = user;
        this.key = key;
    }
}
