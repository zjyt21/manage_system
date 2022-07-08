package com.hlp.service;

import com.hlp.common.Result;
import com.hlp.controller.dto.UserDTO;
import com.hlp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hlp
 * @since 2022-06-23
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
