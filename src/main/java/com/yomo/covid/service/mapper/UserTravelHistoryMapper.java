package com.yomo.covid.service.mapper;


import com.yomo.covid.domain.*;
import com.yomo.covid.service.dto.UserTravelHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserTravelHistory} and its DTO {@link UserTravelHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserTravelHistoryMapper extends EntityMapper<UserTravelHistoryDTO, UserTravelHistory> {



    default UserTravelHistory fromId(String id) {
        if (id == null) {
            return null;
        }
        UserTravelHistory userTravelHistory = new UserTravelHistory();
        userTravelHistory.setId(id);
        return userTravelHistory;
    }
}
