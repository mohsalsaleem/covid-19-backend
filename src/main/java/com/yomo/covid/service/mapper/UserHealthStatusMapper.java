package com.yomo.covid.service.mapper;


import com.yomo.covid.domain.*;
import com.yomo.covid.service.dto.UserHealthStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserHealthStatus} and its DTO {@link UserHealthStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserHealthStatusMapper extends EntityMapper<UserHealthStatusDTO, UserHealthStatus> {



    default UserHealthStatus fromId(String id) {
        if (id == null) {
            return null;
        }
        UserHealthStatus userHealthStatus = new UserHealthStatus();
        userHealthStatus.setId(id);
        return userHealthStatus;
    }
}
