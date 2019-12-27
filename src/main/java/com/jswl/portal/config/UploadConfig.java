package com.jswl.portal.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.jswl.portal.handler.SessionInfoHandler;
@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private SessionInfoHandler sessionInfoHandler;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/admin/upload/**").addResourceLocations("file:d:/images/");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInfoHandler)//
		//拦截的路径
		.addPathPatterns("/admin/**")//
		//排除的路径
		.excludePathPatterns("/admin/login","/admin/doLogin");
	}
	
}
