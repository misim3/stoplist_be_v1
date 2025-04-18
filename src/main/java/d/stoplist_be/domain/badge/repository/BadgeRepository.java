package d.stoplist_be.domain.badge.repository;

import d.stoplist_be.domain.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
