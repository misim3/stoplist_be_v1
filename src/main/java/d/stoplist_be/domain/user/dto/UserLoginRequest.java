package d.stoplist_be.domain.user.dto;

public record UserLoginRequest(
        String nickname,
        String password
) {
}
