package hackathon.hercules.model;

import java.time.LocalDateTime;

public record GroupMembership(
        Long id,
        User member,
        Group group,
        LocalDateTime joinedAt
) {}

