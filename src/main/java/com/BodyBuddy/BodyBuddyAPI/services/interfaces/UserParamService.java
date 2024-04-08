package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.UserParam;
import com.BodyBuddy.BodyBuddyAPI.models.dto.UserParamDTO;

import java.util.List;
import java.util.UUID;

public interface UserParamService {

    public List<UserParam> getUserParams(UUID id);

    public UserParam createUserParamFromDTO(UserParamDTO userParamDTO);

    public void updateUserParam(UUID id, UserParam updatedUserParam);

    //public void deleteUserParam(UUID id);
}
