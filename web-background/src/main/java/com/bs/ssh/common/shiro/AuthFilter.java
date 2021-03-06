package com.bs.ssh.common.shiro;

import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.utils.Constants;
import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证过滤器
 *
 * @author Egan
 * @date 2018/11/13 20:37
 **/
public class AuthFilter extends AuthenticatingFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String token = getRequestToken((HttpServletRequest) request);

        if (this.isBlank(token)) {
            return null;
        }

        return new AuthToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = getRequestToken((HttpServletRequest) request);
        String url = ((HttpServletRequest) request).getRequestURI();
        if(url.equals("/user/article/id") && isBlank(token))
            return true;
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = getRequestToken((HttpServletRequest) request);


        if (this.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            response.setContentType("application/json;charset=utf-8");
            JsonBody<String> r = new JsonBody<String>();
            r.setCode(Constants.RESPONSE_FAILED);
            r.setMessage("需要令牌");
            String json = new Gson().toJson(r);
            httpResponse.getWriter().print(json);

            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        response.setContentType("application/json;charset=utf-8");
        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            JsonBody<String> r = new JsonBody<String>();
            r.setCode(Constants.RESPONSE_FAILED);
            r.setMessage("令牌无效");
            r.setData(throwable.getMessage());
            String json = new Gson().toJson(r);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
            log.warn("令牌无效", e1);
        }

        return false;
    }

    /**
     * 获取请求中的token
     */
    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("token");


        if (this.isBlank(token)) {
            token = request.getParameter("token");
        }

        return token;
    }

    private boolean isBlank(String str){
        return str == null || "".equals(str);
    }
}
