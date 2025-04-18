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
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.UserWeeklyGoalsMapping;
import d.stoplist_be.domain.user_weekly_goals_mapping.repository.UserWeeklyGoalsMappingRepository;
import d.stoplist_be.domain.weekly_goal.entity.WeeklyGoal;
import d.stoplist_be.domain.weekly_goal.repository.WeeklyGoalRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository;
    private UserRepository userRepository;
    private PersonaRepository personaRepository;
    private UserPersonaRepository userPersonaRepository;
    private WeeklyGoalRepository weeklyGoalRepository;

    PersonaService(UserRepository userRepository, PersonaRepository personaRepository, UserPersonaRepository userPersonaRepository, UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository, WeeklyGoalRepository weeklyGoalRepository) {
        this.userRepository = userRepository;
        this.personaRepository = personaRepository;
        this.userPersonaRepository = userPersonaRepository;
        this.userWeeklyGoalsMappingRepository = userWeeklyGoalsMappingRepository;
        this.weeklyGoalRepository = weeklyGoalRepository;
    }

    public UserInfoResponseDto searchPersona(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not found User By userId for PersonaService.searchPersona"));
        List<Persona> personaList = personaRepository.findAll();
        List<Long> userPersonaIds = userPersonaRepository.findByUserId(user.getId())
            .stream()
            .map(UserPersonaMapping::getPersonaId)
            .toList();

        List<PersonaInfo> personaInfos = personaList.stream()
            .map(persona -> new PersonaInfo(persona.getId(),
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


        UserPersonaMapping userPersonaMapping = userPersonaRepository.findByUserIdAndPersonaIdAndStatus(userId, personaId, Status.ON).orElse(null);

        if (userPersonaMapping != null) {
            userPersonaMapping.updateStatus(Status.OFF);

            List<UserWeeklyGoalsMapping> userWeeklyGoalsMappingList = userWeeklyGoalsMappingRepository.findByUserIdAndStatus(userId, Status.ON);
            if (!userWeeklyGoalsMappingList.isEmpty()) {
                for (UserWeeklyGoalsMapping mapping : userWeeklyGoalsMappingList) {
                    mapping.updateStatus(Status.OFF);
                }
            }
        }

        userPersonaRepository.save(new UserPersonaMapping(userId, persona.getId()));

        List<WeeklyGoal> weeklyGoalList = weeklyGoalRepository.findAllByPersonaId(persona.getId());
        List<UserWeeklyGoalsMapping> userWeeklyGoalsMappingList = new ArrayList<>();
        for (WeeklyGoal weeklyGoal : weeklyGoalList) {
            userWeeklyGoalsMappingList.add(new UserWeeklyGoalsMapping(user.getId(), weeklyGoal.getId()));
        }

        userWeeklyGoalsMappingRepository.saveAll(userWeeklyGoalsMappingList);


        return new SelectPersonaResponseDto(user.getNickname(), persona.getName());
    }
}
