package d.stoplist_be.domain.user_persona_mapping.repository;

import d.stoplist_be.domain.user_persona_mapping.entity.UserPersonaMapping;
import java.util.List;
import java.util.Optional;

import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonaRepository extends JpaRepository<UserPersonaMapping, Long> {

    List<UserPersonaMapping> findByUserId(Long id);

    UserPersonaMapping findByIdAndPersonaId(Long userId, Long id);

    Optional<UserPersonaMapping> findByUserIdAndStatus(Long id, Status status);


    Optional<UserPersonaMapping> findByUserIdAndPersonaIdAndStatus(Long userId, Long personaId, Status status);
}
