package com.hlp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hlp.common.Constants;
import com.hlp.controller.dto.UserDTO;
import com.hlp.entity.User;
import com.hlp.exception.ServiceException;
import com.hlp.mapper.UserMapper;
import com.hlp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hlp
 * @since 2022-06-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();
    @Override
    public UserDTO login(UserDTO userDTO) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, userDTO.getUsername());
        lqw.eq(User::getPassword, userDTO.getPassword());
        try {
            User one = getOne(lqw);
            if(one != null){
                BeanUtils.copyProperties(one, userDTO);
                return userDTO;
            }else{
                throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
            }
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
    }
}
