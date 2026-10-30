package com.hz.yk.base.springboot.construct.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author wuzheng.yk
 * @date 2025/6/5
 */
@Component
@Slf4j
public class AService {

    @Resource
    private BService bService;

    @PostConstruct
    public void init() throws InterruptedException {
        log.info("AService init start");
        bService.test();
        log.info("AService init end");
    }

}
