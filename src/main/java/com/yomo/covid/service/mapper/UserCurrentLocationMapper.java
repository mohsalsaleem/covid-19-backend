package com.yomo.covid.service.mapper;


import com.yomo.covid.domain.*;
import com.yomo.covid.service.dto.UserCurrentLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserCurrentLocation} and its DTO {@link UserCurrentLocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserCurrentLocationMapper extends EntityMapper<UserCurrentLocationDTO, UserCurrentLocation> {



    default UserCurrentLocation fromId(String id) {
        if (id == null) {
            return null;
        }
        UserCurrentLocation userCurrentLocation = new UserCurrentLocation();
        userCurrentLocation.setId(id);
        return userCurrentLocation;
    }
}
