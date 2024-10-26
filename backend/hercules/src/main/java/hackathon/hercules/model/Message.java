package hackathon.hercules.model;

import java.time.LocalDateTime;

public record Message(
        Long id,
        User sender,
        User receiver,
        String content,
        LocalDateTime sentAt,
        boolean isRead
) {}
