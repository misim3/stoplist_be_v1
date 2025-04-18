package d.stoplist_be.domain.badge.service;

import d.stoplist_be.domain.badge.dto.BadgeResponse;
import d.stoplist_be.domain.badge.dto.BadgeUserResponse;
import d.stoplist_be.domain.badge.entity.Badge;
import d.stoplist_be.domain.badge.repository.BadgeRepository;
import d.stoplist_be.domain.user.entity.User;
import d.stoplist_be.domain.user.repository.UserRepository;
import d.stoplist_be.domain.user_badge_mapping.entity.UserBadgeMapping;
import d.stoplist_be.domain.user_badge_mapping.repository.UserBadgeMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
    private final UserBadgeMappingRepository userBadgeMappingRepository;

    public List<BadgeResponse> getBadgeList() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(Badge::toDto)
                .toList();
    }

    public List<BadgeUserResponse> getBadgeByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 1) 유저가 획득한 badgeId 집합 생성
        Set<Long> ownedBadgeIds = userBadgeMappingRepository
                .findAllByUserId(userId).stream()
                .map(UserBadgeMapping::getBadgeId)
                .collect(Collectors.toSet());

        // 2) 모든 badge 순회하며, 소유 여부만 체크해 DTO 생성
        return badgeRepository.findAll().stream()
                .map(badge -> new BadgeUserResponse(
                        badge.getName(),
                        ownedBadgeIds.contains(badge.getId())
                ))
                .collect(Collectors.toList());
    }

}
