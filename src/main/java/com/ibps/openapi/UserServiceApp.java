package com.ibps.openapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class UserServiceApp extends SpringBootServletInitializer {
   // private static final Class<UserServiceApp> applicationClass = UserServiceApp.class;

    public static void main(String[] args) {
      //  SpringApplication.run(applicationClass, args);
        new SpringApplicationBuilder(UserServiceApp.class)
              //  initializers(new SpringApplicationContextInitializer())
                .application()
                .run(args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(applicationClass);
//    }
}
