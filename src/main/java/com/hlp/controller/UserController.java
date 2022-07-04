package com.hlp.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlp.entity.User;
import com.hlp.service.IUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<User> allUser(){
        return iUserService.list();
    }

    //新增和修改user
    @PostMapping
    public boolean saveOrUpdateUser(@RequestBody User user){
        return iUserService.saveOrUpdate(user);
    }

    //批量删除
    @PostMapping("/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return iUserService.removeByIds(ids);
    }

    //删除user
    @DeleteMapping("{id}")
    public boolean deleteUserById(@PathVariable Integer id){
        return iUserService.removeById(id);
    }

    //分页查询
    @GetMapping("/page")
    public IPage<User> pageSelect(@RequestParam int currentPage,
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
        return page;
    }
}
