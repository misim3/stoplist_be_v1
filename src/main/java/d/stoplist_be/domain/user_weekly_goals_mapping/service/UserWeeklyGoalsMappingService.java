package d.stoplist_be.domain.user_weekly_goals_mapping.service;

import d.stoplist_be.domain.user_weekly_goals_mapping.dto.UpdateGoalRequest;
import d.stoplist_be.domain.user_weekly_goals_mapping.entity.UserWeeklyGoalsMapping;
import d.stoplist_be.domain.user_weekly_goals_mapping.repository.UserWeeklyGoalsMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserWeeklyGoalsMappingService {

    private final UserWeeklyGoalsMappingRepository userWeeklyGoalsMappingRepository;

    @Transactional
    public void updateGoalFlag(UpdateGoalRequest request) {
        UserWeeklyGoalsMapping userWeeklyGoalsMapping = userWeeklyGoalsMappingRepository.findById(request.id()).orElseThrow(
                () -> new IllegalArgumentException("없는 매핑 정보 입니다.")
        );

        if(request.flag()){
            userWeeklyGoalsMapping.upCount();
        }else{
            userWeeklyGoalsMapping.downCount();
        }
    }
}
