package d.stoplist_be.domain.weekly_goal.service;

import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.UserWeeklyGoalsMapping;
import d.stoplist_be.domain.user_weekly_goals_mapping.repository.UserWeeklyGoalsMappingRepository;
import d.stoplist_be.domain.weekly_goal.GetGoalResponseDto;
import d.stoplist_be.domain.weekly_goal.entity.WeeklyGoal;
import d.stoplist_be.domain.weekly_goal.repository.WeeklyGoalRepository;
import d.stoplist_be.global.entity.BaseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;

@Service
public class GoalService {

    private WeeklyGoalRepository goalRepository;
    private UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository;

    GoalService(WeeklyGoalRepository goalRepository, UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository) {
        this.goalRepository = goalRepository;
        this.userWeeklyGoalsMappingRepository = userWeeklyGoalsMappingRepository;
    }

    public GetGoalResponseDto getGoal(Long userId) {
        System.out.println(userId);
        UserWeeklyGoalsMapping mapping = userWeeklyGoalsMappingRepository.findByUserIdAndStatus(userId,
            Status.ON).stream()
            .max(Comparator.comparing(BaseEntity::getUpdatedAt))
            .orElseThrow(() -> new RuntimeException("Not found user weekly_goal mapping GoalService.getGoal"));

        WeeklyGoal goal = goalRepository.findById(mapping.getWeeklyGoalsId()).orElseThrow(() -> new RuntimeException("Not found weekly_goal mapping GoalService.getGoal"));

        boolean isToday = mapping.getUpdatedAt().toLocalDate().isEqual(LocalDate.now());

        return new GetGoalResponseDto(goal.getContent(), goal.getWeek(), isToday, mapping.getId());
    }
}
