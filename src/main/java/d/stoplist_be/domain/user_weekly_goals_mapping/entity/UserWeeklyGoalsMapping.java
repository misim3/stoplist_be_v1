package d.stoplist_be.domain.user_weekly_goals_mapping.entity;

import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user_weekly_goals_mapping")
@Getter
public class UserWeeklyGoalsMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long weeklyGoalsId;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private int goalsCount;
}
