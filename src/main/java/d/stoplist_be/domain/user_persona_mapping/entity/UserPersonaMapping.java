package d.stoplist_be.domain.user_persona_mapping.entity;

import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_persona_mapping")
@Getter
@Builder
@AllArgsConstructor
public class UserPersonaMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long personaId;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ON;

    public UserPersonaMapping() {}

    public UserPersonaMapping(Long userId, Long personaId) {
        this.userId = userId;
        this.personaId = personaId;
    }

    public static UserPersonaMapping toEntity(Long userId, Long personaId) {
        UserPersonaMapping userPersonaMapping = new UserPersonaMapping();
        userPersonaMapping.userId = userId;
        userPersonaMapping.personaId = personaId;
        return userPersonaMapping;
    }
}
