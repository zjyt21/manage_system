package com.hlp.mapper;

import com.hlp.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hlp
 * @since 2022-06-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
