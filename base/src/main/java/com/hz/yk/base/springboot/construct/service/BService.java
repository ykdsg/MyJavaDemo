package com.hz.yk.base.springboot.construct.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wuzheng.yk
 * @date 2025/6/5
 */
@Component
@Slf4j
@DependsOn("AService")
public class BService {

    @PostConstruct
    public void init() throws InterruptedException {
        log.info("BService init start");
        Thread.sleep(1000);
        log.info("BService init end");
    }

    public void test() {
        log.info("BService test run");
    }

}
