package hackathon.hercules.model;

import java.time.LocalDateTime;

public record FileUpload(
        Long id,
        User uploader,
        String fileName,
        String fileType,
        byte[] data, // Alternativ nur den Pfad speichern
        LocalDateTime uploadedAt
) {
}

