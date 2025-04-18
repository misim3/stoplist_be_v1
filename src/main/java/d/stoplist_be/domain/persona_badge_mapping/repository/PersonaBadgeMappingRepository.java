package d.stoplist_be.domain.persona_badge_mapping.repository;

import d.stoplist_be.domain.persona_badge_mapping.entitiy.PersonaBadgeMapping;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaBadgeMappingRepository extends JpaRepository<PersonaBadgeMapping, Long> {

    Optional<PersonaBadgeMapping> findByPersonaId(Long personaId);
}
