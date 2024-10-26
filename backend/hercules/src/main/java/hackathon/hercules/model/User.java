package hackathon.hercules.model;

public record User(
        String username,
        String password,
        TreeCount treeCount
) {
}
