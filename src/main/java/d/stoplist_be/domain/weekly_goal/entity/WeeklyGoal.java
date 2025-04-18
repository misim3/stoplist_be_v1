package d.stoplist_be.domain.weekly_goal.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "weekly_goals")
public class WeeklyGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int week;

    @Column
    private String content;

    @Column
    private Long personaId;
}
