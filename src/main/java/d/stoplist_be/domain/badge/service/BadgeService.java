package d.stoplist_be.domain.badge.service;

import d.stoplist_be.domain.badge.dto.BadgeResponse;
import d.stoplist_be.domain.badge.entity.Badge;
import d.stoplist_be.domain.badge.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

    public List<BadgeResponse> getBadgeList() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(Badge::toDto)
                .toList();
    }
}
