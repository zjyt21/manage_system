package com.hlp.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlp.common.Result;
import com.hlp.entity.Files;
import com.hlp.entity.User;
import com.hlp.service.IFileService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件上传相关接口
 *
 * @author Mr.Han
 * @create 2022-07-10 18:41
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private IFileService iFileService;

    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null) { // 文件已存在
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:9090/file/" + fileUUID;
        }

        //存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        iFileService.save(saveFile);
        return url;
    }

    /**
     * 文件下载接口 http://localhost:9090/file/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();

    }

    /**
     * 通过文件的md5查询文件
     * @param md5
     * @return
     */
    private Files getFileByMd5(String md5){
        //查询文件的md5是否存在
        LambdaQueryWrapper<Files> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Files::getMd5, md5);
        List<Files> filesList = iFileService.list(lqw);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

    //分页查询
    @GetMapping("/page")
    public Result pageSelect(@RequestParam int currentPage,
                             @RequestParam int pageSize,
                             @RequestParam(defaultValue = "") String name){
        LambdaQueryWrapper<Files> lqw = new LambdaQueryWrapper<>();
        name = name.trim();
        lqw.like(Strings.isNotBlank(name), Files::getName, name);
        lqw.orderByDesc(Files::getId);
        IPage<Files> page = new Page(currentPage, pageSize);
        iFileService.page(page, lqw);
        return Result.success(page);
    }

    //批量删除
    @PostMapping("/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.normal(iFileService.removeByIds(ids));
    }

    //删除file
    @DeleteMapping("{id}")
    public Result deleteUserById(@PathVariable Integer id){
        return Result.normal(iFileService.removeById(id));
    }

    //新增和修改file
    @PostMapping("/update")
    public Result saveOrUpdateUser(@RequestBody Files file){
        return Result.normal(iFileService.saveOrUpdate(file));
    }
}
