package d.stoplist_be.domain.user_weekly_goals_mapping.entity;

import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_weekly_goals_mapping")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public UserWeeklyGoalsMapping(Long userId, Long weeklyGoalsId) {
        this.userId = userId;
        this.weeklyGoalsId = weeklyGoalsId;
        this.status = Status.ON;
        this.goalsCount = 0;
    }

    public void upCount() {
        this.goalsCount++;
    }

    public void downCount() {
        this.goalsCount--;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void complete() {
        this.status = Status.COMPLETED;
    }
}
