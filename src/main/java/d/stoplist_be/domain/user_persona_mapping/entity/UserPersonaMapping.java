package d.stoplist_be.domain.user_persona_mapping.entity;

import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user_persona_mapping")
public class UserPersonaMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long personaId;

    public UserPersonaMapping(Long userId, Long personaId) {
        this.userId = userId;
        this.personaId = personaId;
    }
}
