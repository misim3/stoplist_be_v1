package d.stoplist_be.domain.user.service;

import d.stoplist_be.domain.persona.entity.Persona;
import d.stoplist_be.domain.persona.repository.PersonaRepository;
import d.stoplist_be.domain.user.dto.UserLoginRequest;
import d.stoplist_be.domain.user.dto.UserResponse;
import d.stoplist_be.domain.user.dto.UserSignUpRequest;
import d.stoplist_be.domain.user.entity.User;
import d.stoplist_be.domain.user.repository.UserRepository;
import d.stoplist_be.domain.user_persona_mapping.entity.UserPersonaMapping;
import d.stoplist_be.domain.user_persona_mapping.repository.UserPersonaMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserPersonaMappingRepository userPersonaMappingRepository;
    private final PersonaRepository personaRepository;

    @Transactional
    public UserResponse signUp(UserSignUpRequest request) {
        if (userRepository.existsByNickname(request.nickname())) {
            throw new IllegalArgumentException("닉네임이 이미 사용중 입니다.");
        }

        User user = User.toEntity(request);
        userRepository.save(user);

        UserPersonaMapping userPersonaMapping = UserPersonaMapping.toEntity(user.getId(), request.personaId());
        userPersonaMappingRepository.save(userPersonaMapping);

        Persona persona = personaRepository.findById(request.personaId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 페르소나입니다.")
        );

        return UserResponse.fromEntity(user, persona);
    }


    @Transactional
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByNickname(request.nickname()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        if(!user.getPassword().equals(request.password())) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }

        UserPersonaMapping userPersonaMapping = userPersonaMappingRepository.findByUserId(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("페르소나 유저 매핑이 존재하지 않습니다.")
        );

        Persona persona = personaRepository.findById(userPersonaMapping.getPersonaId()).orElseThrow(
                () -> new IllegalArgumentException("페르소나가 존재하지 않습니다.")
        );

        return UserResponse.fromEntity(user, persona);
    }

}
