/**
 * Created by Arkkienkeli on 17.12.2014.
 */
import config.SecurityConfig;
import config.WebConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebConfig.class);
        appContext.register(SecurityConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "SpringDispatcher", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }

    }


    /*public static String CONFIG_LOCATION = "config";

    public static void main(String[] args) throws Exception {

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.addServlet(new ServletHolder("dispatcher", createDispatcherServlet()), "/");
        context.setResourceBase("controller");

        Server server = new Server(8081);
        server.setHandler(context);

        server.start();
        server.join();
    }

    public static Servlet createDispatcherServlet() {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        servlet.setContextConfigLocation(CONFIG_LOCATION);
        return servlet;
    }*/
