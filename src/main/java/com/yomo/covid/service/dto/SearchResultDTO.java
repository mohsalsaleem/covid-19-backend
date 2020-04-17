package com.yomo.covid.service.dto;

import com.yomo.covid.domain.UserHealthStatus;

import java.io.Serializable;
import java.util.List;

public class SearchResultDTO implements Serializable {
    public String userId;
    public UserDTO userDTO;
    public List<UserHealthStatusDTO> userHealthStatusDTOs;
    public List<UserTravelHistoryDTO> userTravelHistoryDTO;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<UserHealthStatusDTO> getUserHealthStatusDTOs() {
        return userHealthStatusDTOs;
    }

    public void setUserHealthStatusDTOs(List<UserHealthStatusDTO> userHealthStatusDTOs) {
        this.userHealthStatusDTOs = userHealthStatusDTOs;
    }

    public List<UserTravelHistoryDTO> getUserTravelHistoryDTO() {
        return userTravelHistoryDTO;
    }

    public void setUserTravelHistoryDTO(List<UserTravelHistoryDTO> userTravelHistoryDTO) {
        this.userTravelHistoryDTO = userTravelHistoryDTO;
    }
}
