package d.stoplist_be.domain.user_persona_mapping.repository;

import d.stoplist_be.domain.user_persona_mapping.entity.UserPersonaMapping;
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPersonaMappingRepository extends JpaRepository<UserPersonaMapping, Long> {
    Optional<UserPersonaMapping> findByUserId(Long id);

    Optional<UserPersonaMapping> findByUserIdAndStatus(Long userId, Status status);
}
