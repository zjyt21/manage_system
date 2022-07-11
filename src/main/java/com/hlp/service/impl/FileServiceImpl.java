package com.hlp.service.impl;

import com.hlp.entity.Files;
import com.hlp.mapper.FileMapper;
import com.hlp.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hlp
 * @since 2022-07-11
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements IFileService {

}
