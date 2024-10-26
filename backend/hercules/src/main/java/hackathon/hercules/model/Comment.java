package hackathon.hercules.model;

import java.time.LocalDateTime;

public record Comment(
        Long id,
        User author,
        Post post,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        int likeCount
) {}
