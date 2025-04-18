package d.stoplist_be.domain.user_weekly_goals_mapping.repository;

import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.UserWeeklyGoalsMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWeeklyGoalsMappingRepository extends JpaRepository<UserWeeklyGoalsMapping, Long> {
    List<UserWeeklyGoalsMapping> findByUserIdAndStatus(Long userId, Status status);

    List<UserWeeklyGoalsMapping> findAllByGoalsCount(int i);
}
