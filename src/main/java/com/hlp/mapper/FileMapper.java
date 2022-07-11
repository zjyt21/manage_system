package com.hlp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hlp.entity.Files;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hlp
 * @since 2022-07-11
 */
@Mapper
public interface FileMapper extends BaseMapper<Files> {

}
