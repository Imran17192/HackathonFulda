package hackathon.hercules.model;

import java.time.LocalDateTime;

public record Post(
        Long id,
        User author,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        int likeCount,
        int commentCount
) {}

