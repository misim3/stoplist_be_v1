package d.stoplist_be.domain.weekly_goal.repository;

import d.stoplist_be.domain.weekly_goal.entity.WeeklyGoal;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyGoalRepository extends JpaRepository<WeeklyGoal, Integer> {

    List<WeeklyGoal> findAllByPersonaId(Long id);

    Optional<WeeklyGoal> findById(Long weeklyGoalsId);
}
