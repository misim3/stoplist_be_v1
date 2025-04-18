package d.stoplist_be.domain.persona.repository;

import d.stoplist_be.domain.persona.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByName(String personaName);
}
