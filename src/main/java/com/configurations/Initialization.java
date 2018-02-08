package com.configurations;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initialization implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {

	AnnotationConfigWebApplicationContext cx = new AnnotationConfigWebApplicationContext();
	cx.register(ViewResolverConfiguration.class);
	cx.setServletContext(container);
	ServletRegistration.Dynamic ser = container.addServlet("dispatcher", new DispatcherServlet(cx));
	ser.setLoadOnStartup(1);
	ser.addMapping("/");
    }
}
