package com.lxp.user.domain.user.repository;

import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.model.vo.UserStatus;

import java.util.UUID;

public interface UserRepository {

    UserStatus findUserStatusById(UserId userId);

    void save(User user);

}
