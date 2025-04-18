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
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.UserWeeklyGoalsMapping;
import d.stoplist_be.domain.user_weekly_goals_mapping.repository.UserWeeklyGoalsMappingRepository;
import d.stoplist_be.domain.weekly_goal.entity.WeeklyGoal;
import d.stoplist_be.domain.weekly_goal.repository.WeeklyGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserPersonaMappingRepository userPersonaMappingRepository;
    private final PersonaRepository personaRepository;
    private final UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository;
    private final WeeklyGoalRepository weeklyGoalRepository;

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

        List<WeeklyGoal> weeklyGoalList = weeklyGoalRepository.findAllByPersonaId(persona.getId());
        List<UserWeeklyGoalsMapping> userWeeklyGoalsMappingList = new ArrayList<>();
        for (WeeklyGoal weeklyGoal : weeklyGoalList) {
            userWeeklyGoalsMappingList.add(new UserWeeklyGoalsMapping(user.getId(), weeklyGoal.getId()));
        }

        userWeeklyGoalsMappingRepository.saveAll(userWeeklyGoalsMappingList);

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

        UserPersonaMapping userPersonaMapping = userPersonaMappingRepository.findByUserIdAndStatus(user.getId(), Status.ON).orElseThrow(
                () -> new IllegalArgumentException("페르소나 유저 매핑이 존재하지 않습니다.")
        );

        Persona persona = personaRepository.findById(userPersonaMapping.getPersonaId()).orElseThrow(
                () -> new IllegalArgumentException("페르소나가 존재하지 않습니다.")
        );

        return UserResponse.fromEntity(user, persona);
    }

}
