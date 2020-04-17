package com.yomo.covid.service.mapper;


import com.yomo.covid.domain.*;
import com.yomo.covid.service.dto.UserSymptomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserSymptom} and its DTO {@link UserSymptomDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserSymptomMapper extends EntityMapper<UserSymptomDTO, UserSymptom> {



    default UserSymptom fromId(String id) {
        if (id == null) {
            return null;
        }
        UserSymptom userSymptom = new UserSymptom();
        userSymptom.setId(id);
        return userSymptom;
    }
}
