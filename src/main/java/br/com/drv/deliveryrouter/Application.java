package br.com.drv.deliveryrouter;


import java.util.Arrays;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

    }
    
    @Bean
    public ServletRegistrationBean h2Servlet() {
    	ServletRegistrationBean servletBean = new ServletRegistrationBean();
    	servletBean.addUrlMappings("/h2/*");
    	servletBean.setServlet(new WebServlet());
    	return servletBean;
    }

}