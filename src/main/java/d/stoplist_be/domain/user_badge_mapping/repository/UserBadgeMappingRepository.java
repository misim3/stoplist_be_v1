package d.stoplist_be.domain.user_badge_mapping.repository;

import d.stoplist_be.domain.user_badge_mapping.entity.UserBadgeMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeMappingRepository extends JpaRepository<UserBadgeMapping, Long> {
    List<UserBadgeMapping> findAllByUserId(Long userId);
}
