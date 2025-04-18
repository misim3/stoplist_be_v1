package d.stoplist_be.domain.weekly_goal.repository;

import d.stoplist_be.domain.weekly_goal.entity.WeeklyGoal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyGoalRepository extends JpaRepository<WeeklyGoal, Integer> {

    Optional<WeeklyGoal> findById(Long weeklyGoalsId);
}
