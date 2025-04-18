package d.stoplist_be.domain.persona;

import java.util.List;

public record UserInfoResponseDto(String nickname, List<PersonaInfo> personas) {

}
