package hackathon.hercules.model;

import java.time.LocalDateTime;

public record Follow(
        Long id,
        User follower,
        User following,
        LocalDateTime createdAt
) {}

