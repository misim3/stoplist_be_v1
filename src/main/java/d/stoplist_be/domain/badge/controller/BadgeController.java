package d.stoplist_be.domain.badge.controller;


import d.stoplist_be.domain.badge.service.BadgeService;
import d.stoplist_be.global.dto.ApiResponse;
import d.stoplist_be.global.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/badge")
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping
    public ApiResponse<?> getBadgeList() {
        return ResponseUtils.success(badgeService.getBadgeList());
    }

    @GetMapping("/{userId}")
    public ApiResponse<?> getBadgeListByUserId(@PathVariable("userId") Long userId) {
        return ResponseUtils.success(badgeService.getBadgeByUser(userId));
    }
}
