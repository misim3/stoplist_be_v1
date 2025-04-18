package d.stoplist_be.domain.user_weekly_goals_mapping.entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWeeklyGoalsMappingRepository extends
    JpaRepository<UserWeeklyGoalsMapping, Long> {

    List<UserWeeklyGoalsMapping> findByUserIdAndStatus(Long userId, Status status);
}
