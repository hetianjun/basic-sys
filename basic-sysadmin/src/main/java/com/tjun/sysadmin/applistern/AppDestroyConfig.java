package com.tjun.sysadmin.applistern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Slf4j
@Component
public class AppDestroyConfig {

    @PreDestroy
    public void PreDestroy() throws Exception {

        log.error("------------------应用程序正在关闭。。。");

    }
}
