package hackathon.hercules.model;

import java.time.LocalDateTime;

public record Like(
        Long id,
        User user,
        Post post, // Falls der Like für einen Beitrag ist
        Comment comment, // Falls der Like für einen Kommentar ist
        LocalDateTime createdAt
) {}

