package d.stoplist_be.domain.persona_badge_mapping.entitiy;

import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "persona_badge_mappings")
@Getter
public class PersonaBadgeMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long personaId;

    @Column
    private Long badgeId;

    public PersonaBadgeMapping(Long personaId, Long badgeId) {
        this.personaId = personaId;
        this.badgeId = badgeId;
    }

    public PersonaBadgeMapping() {

    }
}
