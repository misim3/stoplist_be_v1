package d.stoplist_be.domain.user_persona_mapping.repository;

import d.stoplist_be.domain.user_persona_mapping.entity.UserPersonaMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonaRepository extends JpaRepository<UserPersonaMapping, Long> {

    List<UserPersonaMapping> findByUserId(Long id);

    UserPersonaMapping findByIdAndPersonaId(Long userId, Long id);
}
