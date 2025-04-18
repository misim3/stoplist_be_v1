package d.stoplist_be.domain.user.dto;

public record UserSignUpRequest(
        String nickname,
        String password,
        Long personaId
) {
}
