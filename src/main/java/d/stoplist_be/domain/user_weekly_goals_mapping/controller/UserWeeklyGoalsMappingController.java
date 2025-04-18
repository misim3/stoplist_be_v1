package d.stoplist_be.domain.user_weekly_goals_mapping.controller;


import d.stoplist_be.domain.user_weekly_goals_mapping.dto.UpdateGoalRequest;
import d.stoplist_be.domain.user_weekly_goals_mapping.service.UserWeeklyGoalsMappingService;
import d.stoplist_be.global.dto.ApiResponse;
import d.stoplist_be.global.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserWeeklyGoalsMappingController {
    private final UserWeeklyGoalsMappingService userWeeklyGoalsMappingService;

    @PostMapping("/api/goals/flag")
    public ApiResponse<?> updateGoalFlag(@RequestBody UpdateGoalRequest request) {
        userWeeklyGoalsMappingService.updateGoalFlag(request);
        return ResponseUtils.success();
    }
}
