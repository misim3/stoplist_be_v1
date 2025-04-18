package d.stoplist_be.domain.persona.repository;

import d.stoplist_be.domain.persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
