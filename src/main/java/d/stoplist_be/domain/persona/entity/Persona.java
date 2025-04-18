package d.stoplist_be.domain.persona.entity;

import d.stoplist_be.domain.user_weekly_goals_mapping.entity.Status;
import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "personas")
public class Persona extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}
