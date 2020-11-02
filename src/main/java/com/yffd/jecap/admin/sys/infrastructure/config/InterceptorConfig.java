package com.yffd.jecap.admin.sys.infrastructure.config;

import com.yffd.jecap.admin.sys.application.security.authc.aop.LoginHandleInterceptor;
import com.yffd.jecap.admin.sys.application.security.authz.aop.RequiresPmsnsAnnotationMethodInterceptor;
import com.yffd.jecap.admin.sys.application.security.handler.ISecurityAccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired private ISecurityAccessHandler securityAccessHandler;

    private String[] pathPatterns = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/login/doLogin",
            "/login/doLogout"
    };

    @Value("${security.skip:false}")
    private boolean securitySkip;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginHandleInterceptor()).excludePathPatterns(pathPatterns);
        registry.addInterceptor(this.requiresPmsnsAnnotationMethodInterceptor()).excludePathPatterns(pathPatterns);
    }

    @Bean
    public LoginHandleInterceptor loginHandleInterceptor() {
        LoginHandleInterceptor interceptor = new LoginHandleInterceptor(this.securityAccessHandler);
        interceptor.setSkip(this.securitySkip);
        return interceptor;
    }

    @Bean
    public RequiresPmsnsAnnotationMethodInterceptor requiresPmsnsAnnotationMethodInterceptor() {
        RequiresPmsnsAnnotationMethodInterceptor interceptor = new RequiresPmsnsAnnotationMethodInterceptor(this.securityAccessHandler);
        interceptor.setSkip(this.securitySkip);
        return interceptor;
    }

}
