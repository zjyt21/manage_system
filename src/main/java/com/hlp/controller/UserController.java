package com.hlp.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlp.common.Constants;
import com.hlp.common.Result;
import com.hlp.controller.dto.UserDTO;
import com.hlp.entity.Files;
import com.hlp.entity.User;
import com.hlp.service.IUserService;
import com.hlp.util.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hlp
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    //查询所有user
    @GetMapping
    public Result allUser(){
        return Result.success(iUserService.list());
    }

    //新增和修改user
    @PostMapping
    public Result saveOrUpdateUser(@RequestBody User user){
        return Result.normal(iUserService.saveOrUpdate(user));
    }

    //批量删除
    @PostMapping("/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.normal(iUserService.removeByIds(ids));
    }

    //删除user
    @DeleteMapping("{id}")
    public Result deleteUserById(@PathVariable Integer id){
        return Result.normal(iUserService.removeById(id));
    }

    //分页查询
    @GetMapping("/page")
    public Result pageSelect(@RequestParam int currentPage,
                                  @RequestParam int pageSize,
                                  @RequestParam(defaultValue = "") String username,
                                  @RequestParam(defaultValue = "") String email,
                                  @RequestParam(defaultValue = "") String address){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        username = username.trim();
        email = email.trim();
        address = address.trim();
        lqw.like(Strings.isNotBlank(username), User::getUsername, username);
        lqw.like(Strings.isNotBlank(email), User::getEmail, email);
        lqw.like(Strings.isNotBlank(address), User::getAddress, address);
        lqw.orderByDesc(User::getId);
        IPage<User> page = new Page(currentPage, pageSize);
        iUserService.page(page, lqw);
        //获取当前用户信息
        User currentUser = TokenUtils.getCurrentUser();
        System.out.println("获取当前用户信息=============" + currentUser.getNickname());
        return Result.success(page);
    }

    //导出接口
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = iUserService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    //导入
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        List<User> list = reader.readAll(User.class);
        return Result.normal(iUserService.saveBatch(list));
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = iUserService.login(userDTO);
        return Result.success(dto);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(iUserService.register(userDTO));
    }

    //通过用户名获取用户信息
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Strings.isNotBlank(username), User::getUsername, username);
        return Result.success(iUserService.getOne(lqw));
    }
}
