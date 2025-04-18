package d.stoplist_be.domain.weekly_goal.controller;

import d.stoplist_be.domain.weekly_goal.GetGoalResponseDto;
import d.stoplist_be.domain.weekly_goal.service.GoalService;
import d.stoplist_be.global.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goal")
public class GoalController {

    private GoalService goalService;

    GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public ApiResponse<GetGoalResponseDto> getGoal(Long userId) {
        return new ApiResponse<>(200, "ok", goalService.getGoal(userId));
    }


}
