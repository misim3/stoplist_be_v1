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
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("유저를 찾을 수 없습니다.")
        );

        List<Badge> badgeList = badgeRepository.findAll();
        List<UserBadgeMapping> userBadgeMappingList = userBadgeMappingRepository.findAllByUserId(user.getId());
        List<BadgeUserResponse> badgeResponseList = new ArrayList<>();
        for (Badge badge : badgeList) {
            if (!userBadgeMappingList.isEmpty()){
                for (UserBadgeMapping userBadgeMapping : userBadgeMappingList) {
                    if (Objects.equals(userBadgeMapping.getBadgeId(), badge.getId())){
                        badgeResponseList.add(
                                new BadgeUserResponse(
                                        badge.getName(),
                                        true
                                )
                        );
                    } else {
                        badgeResponseList.add(
                                new BadgeUserResponse(
                                        badge.getName(),
                                        false
                                )
                        );
                    }
                }
            }
            badgeResponseList.add(
                    new BadgeUserResponse(
                            badge.getName(),
                            false
                    )
            );
        }
        return badgeResponseList;
    }
}
