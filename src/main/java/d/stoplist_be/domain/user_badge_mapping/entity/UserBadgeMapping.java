package d.stoplist_be.domain.user_badge_mapping.entity;

import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user_badge_mapping")
@Getter
public class UserBadgeMapping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long badgeId;
}
