package hackathon.hercules.model;

public record PostTag(
        Long id,
        Post post,
        Tag tag
) {}
