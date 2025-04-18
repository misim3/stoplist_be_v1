package d.stoplist_be.domain.user_weekly_goals_mapping.repository;

import d.stoplist_be.domain.user_weekly_goals_mapping.entity.UserWeeklyGoalsMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWeeklyGoalsMappingRepository extends JpaRepository<UserWeeklyGoalsMapping, Long> {
}
