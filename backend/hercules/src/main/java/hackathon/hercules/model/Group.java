package hackathon.hercules.model;

import java.time.LocalDateTime;

public record Group(
        Long id,
        String name,
        String description,
        User owner,
        LocalDateTime createdAt
) {}
