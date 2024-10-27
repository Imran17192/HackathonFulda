package hackathon.hercules.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PostEntity extends ObjectEntity{


    private Long post_id;

    private String text;

    public PostEntity() {
    }

    public PostEntity(Long post_id, String text) {
        this.post_id = post_id;
        this.text = text;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
