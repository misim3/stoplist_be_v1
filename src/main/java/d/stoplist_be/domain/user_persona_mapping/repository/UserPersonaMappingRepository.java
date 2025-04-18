package d.stoplist_be.domain.user_persona_mapping.repository;

import d.stoplist_be.domain.user_persona_mapping.entity.UserPersonaMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPersonaMappingRepository extends JpaRepository<UserPersonaMapping, Long> {
    Optional<UserPersonaMapping> findByUserId(Long id);
}
