package d.stoplist_be.domain.user.dto;

import d.stoplist_be.domain.persona.entity.Persona;
import d.stoplist_be.domain.user.entity.User;

public record UserResponse(
        Long userId,
        String nickname,
        Long personaId
) {
    public static UserResponse fromEntity(User user, Persona persona) {
        return new UserResponse(
                user.getId(),
                user.getNickname(),
                persona.getId()
        );
    }
}
