package com.hlp.util;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hlp.entity.User;
import com.hlp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Mr.Han
 * @create 2022-07-09 18:19
 */
@Component
public class TokenUtils {

    private static IUserService staticIUserService;

    @Resource
    private IUserService iUserService;

    @PostConstruct
    public void setiUserService(){
        staticIUserService = iUserService;
    }
    /**
     * 生成token
     * @return
     */
    public static String getToken(String userId, String sign){
        return JWT.create().withAudience(userId) //将user id保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) //2小时有效期
                .sign(Algorithm.HMAC256(sign)); //以password作为token的密钥
    }

    /**
     * 获取当前登录的用户信息
     * @return user对象
     */
    public static User getCurrentUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");

            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticIUserService.getById(Integer.valueOf(userId));
            }

        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
