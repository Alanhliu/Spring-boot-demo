package com.example.demo.Interceptor;

import com.example.demo.exception.AuthFailedException;
import com.example.demo.service.RedisService;
import com.example.demo.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import com.example.demo.annotation.Authorization;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisService redisService;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String requestToken = request.getHeader("demo_token");

        if (requestToken == null) {
            throw new AuthFailedException(new Error(Error.USER_AUTH_FAILED));
        }

        String uid = (String) redisService.get(requestToken);
        if (uid == null) {
            throw new AuthFailedException(new Error(Error.USER_AUTH_FAILED));
        }
        String currentToken = (String) redisService.get(uid);

        if (currentToken != null && !currentToken.equals(requestToken)) {
            redisService.delete(requestToken);
            System.out.println("您的账号在其他设备登录!");
            throw new AuthFailedException(new Error(Error.USER_AUTH_OTHER_DEVICE));
        }

        if (requestToken != null && uid != null) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute("uid", uid);
            return true;
        }

        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,Error.getMessage(Error.USER_AUTH_FAILED));
//            return false;

            throw new AuthFailedException(new Error(Error.USER_AUTH_FAILED));
        }

        return true;
    }
}
