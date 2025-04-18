package d.stoplist_be.domain.user.entity;

import d.stoplist_be.domain.user.dto.UserSignUpRequest;
import d.stoplist_be.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String password;


    public static User toEntity(UserSignUpRequest request) {
        User user = new User();
        user.nickname = request.nickname();
        user.password = request.password();
        return user;
    }
}
