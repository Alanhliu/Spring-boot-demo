package com.example.demo.Interceptor;

import com.example.demo.exception.AuthFailedException;
import com.example.demo.util.Error;
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


    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getHeader("demo_token");

        if (authorization != null && authorization.equals("123456")) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute("uid", 1);
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
