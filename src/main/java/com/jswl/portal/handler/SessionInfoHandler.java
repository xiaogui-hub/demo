package com.jswl.portal.handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jswl.portal.common.util.ResultUtil;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.User;
@Component
public class SessionInfoHandler implements HandlerInterceptor{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在拦截之前先判断当前回话中是否有用户信息，如果没有，那么跳转到登录页面
		User user = (User)request.getSession().getAttribute("user");
		if(user==null) {
			// 判断是否为ajax请求,如果是ajax请求那么我们返回ResultDto的json格式信息，通知用户登录
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // 如果是ajax请求响应头会有x-requested-with
				response.setContentType("application/json;charset=utf-8");
				ResultDto<String> dto = ResultUtil.fail("用户未登录，请登录");
				objectMapper.writeValue(response.getOutputStream(), dto);
				return false;
			}
			//如果不是ajax请求，直接进行跳转到登录页面
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
