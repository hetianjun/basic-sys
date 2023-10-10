package com.tjun.sysadmin;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


//@EnableScheduling
//@ComponentScan(basePackages={"cn.hutool.extra.spring"})
@Slf4j
@SpringBootApplication
public class SysAdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SysAdminApplication.class, args);


        log.info("---------AdminApplication----Start------------run");
        Environment environment = SpringUtil.getBean(Environment.class);
        log.info("访问链接：http://"+ "localhost"+":"
                +environment.getProperty("server.port") +environment.getProperty("server.servlet.context-path") +"/doc.html");
    }





    @Controller
    public static class WelcomeController {

        @RequestMapping({"/"})
        public @ResponseBody JSONObject idx(HttpServletRequest request){
            return new JSONObject().set("code","200").set("msg","成功").set("data","");
        }
    }

}
