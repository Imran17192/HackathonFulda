package hackathon.hercules.model;

import java.time.LocalDateTime;

public record TreeCount(
        Long id,
        User user,               // Der Benutzer, dem die Baumanzahl gehört
        int treeCount,           // Anzahl der "Bäume", die der Benutzer gesammelt hat
        LocalDateTime updatedAt  // Zeit der letzten Aktualisierung
) {}

