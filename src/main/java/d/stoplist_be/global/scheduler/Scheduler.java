package d.stoplist_be.global.scheduler;

import d.stoplist_be.domain.persona_badge_mapping.entitiy.PersonaBadgeMapping;
import d.stoplist_be.domain.persona_badge_mapping.repository.PersonaBadgeMappingRepository;
import d.stoplist_be.domain.user_badge_mapping.entity.UserBadgeMapping;
import d.stoplist_be.domain.user_badge_mapping.repository.UserBadgeMappingRepository;
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.UserWeeklyGoalsMapping;
import d.stoplist_be.domain.user_weekly_goals_mapping.repository.UserWeeklyGoalsMappingRepository;
import d.stoplist_be.domain.weekly_goal.entity.WeeklyGoal;
import d.stoplist_be.domain.weekly_goal.repository.WeeklyGoalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository;
    private WeeklyGoalRepository goalRepository;
    private PersonaBadgeMappingRepository personaBadgeMappingRepository;
    private UserBadgeMappingRepository userBadgeMappingRepository;

    Scheduler(UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository, WeeklyGoalRepository goalRepository, PersonaBadgeMappingRepository personaBadgeMappingRepository, UserBadgeMappingRepository userBadgeMappingRepository) {
        this.userWeeklyGoalsMappingRepository = userWeeklyGoalsMappingRepository;
        this.goalRepository = goalRepository;
        this.personaBadgeMappingRepository = personaBadgeMappingRepository;
        this.userBadgeMappingRepository = userBadgeMappingRepository;
    }

    @Scheduled(cron = "30 7 8 * * *")
    public void schedule() {
        System.out.println("start");
        List<UserWeeklyGoalsMapping> mappings = userWeeklyGoalsMappingRepository.findAllByGoalsCount(7);
        mappings.forEach(UserWeeklyGoalsMapping::complete);
        userWeeklyGoalsMappingRepository.saveAll(mappings);

        Map<Long, WeeklyGoal> weeklyGoalMap = goalRepository.findAll().stream()
            .collect(Collectors.toMap(WeeklyGoal::getId, Function.identity()));

        Map<UserPersonaInfo, Long> countMap = mappings.stream()
            .map(mapping -> {
                WeeklyGoal goal = weeklyGoalMap.get(mapping.getWeeklyGoalsId());
                return new UserPersonaInfo(mapping.getUserId(), goal.getPersonaId());
            })
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<UserPersonaInfo> userPersonaInfos = countMap.entrySet().stream()
            .filter(entry -> entry.getValue() >= 3)
            .map(Map.Entry::getKey)
            .toList();

        List<UserBadgeMapping> newMappings = new ArrayList<>();

        for (UserPersonaInfo info : userPersonaInfos) {
            Long personaId = info.personaId();
            Long userId = info.userId();

            Optional<PersonaBadgeMapping> personaBadgeMapping = personaBadgeMappingRepository.findByPersonaId(personaId);

            if (personaBadgeMapping.isPresent()) {
                Long badgeId = personaBadgeMapping.get().getBadgeId();

                boolean alreadyGiven = userBadgeMappingRepository.existsByUserIdAndBadgeId(userId, badgeId);
                if (!alreadyGiven) {
                    newMappings.add(new UserBadgeMapping(userId, badgeId));
                }
            }
        }

        userBadgeMappingRepository.saveAll(newMappings);

        System.out.println("done");
    }
}
