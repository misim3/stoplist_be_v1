package d.stoplist_be.domain.persona.service;

import d.stoplist_be.domain.persona.PersonaInfo;
import d.stoplist_be.domain.persona.PersonaListResponseDto;
import d.stoplist_be.domain.persona.PersonaResponseDto;
import d.stoplist_be.domain.persona.SelectPersonaResponseDto;
import d.stoplist_be.domain.persona.UserInfoResponseDto;
import d.stoplist_be.domain.persona.entity.Persona;
import d.stoplist_be.domain.persona.repository.PersonaRepository;
import d.stoplist_be.domain.user.entity.User;
import d.stoplist_be.domain.user.repository.UserRepository;
import d.stoplist_be.domain.user_persona_mapping.entity.UserPersonaMapping;
import d.stoplist_be.domain.user_persona_mapping.repository.UserPersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private UserRepository userRepository;
    private PersonaRepository personaRepository;
    private UserPersonaRepository userPersonaRepository;

    PersonaService(UserRepository userRepository, PersonaRepository personaRepository, UserPersonaRepository userPersonaRepository) {
        this.userRepository = userRepository;
        this.personaRepository = personaRepository;
        this.userPersonaRepository = userPersonaRepository;
    }

    public UserInfoResponseDto searchPersona(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not found User By userId for PersonaService.searchPersona"));
        List<Persona> personaList = personaRepository.findAll();
        List<Long> userPersonaIds = userPersonaRepository.findByUserId(user.getId())
            .stream()
            .map(UserPersonaMapping::getPersonaId)
            .toList();

        List<PersonaInfo> personaInfos = personaList.stream()
            .map(persona -> new PersonaInfo(
                persona.getName(),
                userPersonaIds.contains(persona.getId())))
            .toList();

        return new UserInfoResponseDto(user.getNickname(), personaInfos);
    }

    public PersonaListResponseDto getPersonaList() {
        return new PersonaListResponseDto(personaRepository.findAll().stream()
            .map(persona -> new PersonaResponseDto(persona.getId(), persona.getName()))
            .toList()
        );
    }

    public SelectPersonaResponseDto updatePersona(Long userId, Long personaId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not found User By userId for PersonaService.updatePersona"));
        Persona persona = personaRepository.findById(personaId).orElseThrow(() -> new EntityNotFoundException("Not found Persona By personaId for PersonaService.updatePersona"));

        userPersonaRepository.save(new UserPersonaMapping(userId, persona.getId()));

        return new SelectPersonaResponseDto(user.getNickname(), persona.getName());
    }
}
