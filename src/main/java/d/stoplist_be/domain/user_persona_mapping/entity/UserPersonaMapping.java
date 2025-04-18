package d.stoplist_be.domain.user_persona_mapping.entity;

import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_persona_mapping")
public class UserPersonaMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long personaId;
}
