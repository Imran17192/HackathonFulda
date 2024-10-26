package hackathon.hercules.entity;

public class ServerEntity extends ObjectEntity{

    private Long server_id;
    private String link;

    public ServerEntity() {}

    public Long getServer_id() {
        return server_id;
    }

    public void setServer_id(Long server_id) {
        this.server_id = server_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ServerEntity(Long server_id, String link) {
        this.server_id = server_id;
        this.link = link;
    }
}
